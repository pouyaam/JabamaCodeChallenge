@file:OptIn(FlowPreview::class)

package com.jabama.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.common.Resource
import com.jabama.domain.usecase.repositories.GetRepositoriesUseCase
import com.jabama.domain.usecase.token.ClearTokenUseCase
import com.jabama.model.RepositoryResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val clearTokenUseCase: ClearTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SearchEffect>()
    val effect = _effect.receiveAsFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("SearchViewModel", "Caught exception: $throwable")
    }

    init {
        observeSearchQuery()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeSearchQuery() {
        viewModelScope.launch(exceptionHandler) {
            _state
                .map { it.searchQuery }
                .debounce(DEBOUNCE)
                .distinctUntilChanged()
                .filter { query -> query?.isNotBlank() == true }
                .onEach { query ->
                    Log.d("SearchVM", "Searching for: $query")
                }
                .flatMapLatest { query ->
                    getRepositoriesUseCase(
                        query.orEmpty(),
                        perPage = PER_PAGE,
                        page = 1
                    )
                        .onStart {
                            _state.update { it.copy(apiIsLoading = true) }
                        }
                        .catch { error ->
                            _state.update {
                                it.copy(
                                    apiIsLoading = false,
                                    isFailed = true,
                                    errorMessage = error.message
                                )
                            }
                            emit(Resource.Error(Exception(error.message)))
                        }
                }
                .collect { result ->
                    handleSearchResults(result)
                }
        }
    }

    fun onEvent(event: SearchEvent) {
        viewModelScope.launch {
            when (event) {
                is SearchEvent.SearchQueryChanged -> {
                    _state.update { it.copy(searchQuery = event.query) }
                }

                SearchEvent.Logout -> {
                    clearTokenUseCase()
                    navigateToLogin()
                }
                SearchEvent.OnClearQueryClick -> {
                    _state.update { it.copy(searchQuery = null) }
                }

                SearchEvent.OnRetryClick -> {
                    _state.update { it.copy(searchQuery = null) }
                }
            }
        }
    }

    private suspend fun navigateToLogin() {
        _effect.send(SearchEffect.NavigateToLogin)
    }

    private fun handleSearchResults(results: Resource<RepositoryResponse>) {
        when (results) {
            is Resource.Success -> {
                val repositories = results.data.items.take(PER_PAGE)
                _state.update {
                    it.copy(
                        apiIsLoading = false,
                        isFailed = repositories.isEmpty(),
                        searchedRepository = repositories,
                        errorMessage = null
                    )
                }
            }

            is Resource.Error -> {
                _state.update {
                    it.copy(
                        apiIsLoading = false,
                        isFailed = true,
                        errorMessage = results.exception?.message ?: "Unknown error"
                    )
                }
            }
        }
    }

    companion object {
        private const val DEBOUNCE = 250L
        private const val PER_PAGE = 30
    }
}