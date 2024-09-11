package com.jabama.challenge.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.common.utils.UiText
import com.jabama.challenge.search.domain.model.SearchResult
import com.jabama.challenge.search.domain.usecase.GetLanguageColorFromName
import com.jabama.challenge.search.domain.usecase.SearchForRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    val searchUseCase: SearchForRepository,
    val languageColorUseCase: GetLanguageColorFromName,
) : ViewModel() {

    private val _effects = MutableSharedFlow<SearchEffects>()
    val effects = _effects.asSharedFlow()

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private var job: Job? = null

    fun invokeEvent(event: SearchEvents) {
        when (event) {
            is SearchEvents.OnQueryChange -> {
                _state.update {
                    it.copy(
                        query = event.query,
                        isHintVisible = event.query.isEmpty(),
                    )
                }
                startSearch()
            }

            is SearchEvents.OnSearch -> {
                startSearch()
            }

            is SearchEvents.OnSearchFocusChange -> {
                _state.update {
                    it.copy(
                        isHintVisible = !event.isFocused
                    )
                }
            }
        }
    }

    private fun startSearch() {
        job?.cancel()
        job = viewModelScope.launch {
            _state.update {
                it.copy(
                    isSearching = true,
                    searchResult = emptyList(),
                )
            }
            val result = searchUseCase(_state.value.query)
            val searchResult: List<SearchResult>
            if (result.isSuccess) {
                searchResult = result.getOrThrow().onEach { each ->
                    each.language?.let { eachLanguage ->
                        each.languageColor = languageColorUseCase(eachLanguage)
                    }
                }
                _effects.emit(SearchEffects.ResultSuccess)
            } else {
                //TODO we should handle exception scenarios later
                //TODO handle auth failure scenario later
                _effects.emit(
                    SearchEffects.ResultFailure(
                        UiText.DynamicString(result.exceptionOrNull()?.message.orEmpty())
                    )
                )
                searchResult = emptyList()
            }

            _state.update {
                it.copy(
                    searchResult = searchResult,
                    isSearching = false,
                )
            }
        }
    }

}