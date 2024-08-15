package com.jabama.challenge.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import com.jabama.challenge.search.model.ErrorType
import com.jabama.challenge.search.model.PageState
import com.jabama.challenge.search.model.RepoSearch
import com.jabama.challenge.search.model.SearchEvent
import com.jabama.challenge.search.model.SearchUiState
import com.jabama.challenge.search.repository.SearchRepository
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    initialUiState: SearchUiState,
    private val repository: SearchRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SearchEvent>()
    val event = _event.asSharedFlow()

    private val _searchText = MutableStateFlow("")

    init {
        _searchText
            .debounce(DEBOUNCE)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                if (query.isEmpty()) {
                    _uiState.update { initialUiState }
                    emptyFlow()
                } else {
                    repository.searchRepos(
                        query = query,
                        page = 1,
                        perPage = PER_PAGE,
                    ).onStart {
                        _uiState.update { it.copy(state = PageState.Loading) }
                    }
                }
            }.onEach { result ->
                handleResult(result)
            }.launchIn(viewModelScope)
    }

    private suspend fun handleResult(result: Result<RepoSearch, GeneralError>) {
        when (result) {
            is Result.Failure -> onSearchFailure(result.error)
            is Result.Success -> onSearchSuccess(result.data)
        }
    }

    private fun onSearchSuccess(data: RepoSearch) {
        val items = buildList {
            addAll(uiState.value.items)
            addAll(data.items)
        }.toPersistentList()

        _uiState.update {
            it.copy(
                state = if (items.isEmpty()) PageState.NoItems else PageState.Idle,
                items = items,
                totalCount = data.totalCount,
                isLastPage = data.items.isEmpty()
            )
        }
    }

    private suspend fun onSearchFailure(error: GeneralError) {
        when (error) {
            is GeneralError.ApiError -> {
                when (error.code) {
                    UNAUTHORIZED_CODE -> _event.emit(SearchEvent.NavigateToLoginPage)
                    SPAMMED_CODE -> updateErrorState(ErrorType.SPAMMED)
                    else -> updateErrorState(ErrorType.GENERAL)
                }
            }

            else -> updateErrorState(ErrorType.GENERAL)
        }
    }

    private fun updateErrorState(type: ErrorType) {
        _uiState.update { it.copy(state = PageState.Error(type)) }
    }


    fun onQueryChange(query: String) {
        _searchText.update { query }
    }

    fun retry() {
        viewModelScope.launch {
            repository.searchRepos(
                query = _searchText.value,
                page = 1,
                perPage = PER_PAGE
            ).onStart {
                _uiState.update { it.copy(state = PageState.Loading) }
            }.collect {
                handleResult(it)
            }
        }
    }

    companion object {
        private const val DEBOUNCE = 300L
        private const val PER_PAGE = 30
        private const val UNAUTHORIZED_CODE = 401
        private const val SPAMMED_CODE = 422
    }
}