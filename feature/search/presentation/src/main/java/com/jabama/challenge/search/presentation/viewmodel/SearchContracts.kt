package com.jabama.challenge.search.presentation.viewmodel

import com.jabama.challenge.common.utils.UiText
import com.jabama.challenge.search.domain.model.SearchResult

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = true,
    val isSearching: Boolean = false,
    val searchResult: List<SearchResult> = emptyList()
)

sealed class SearchEvents {
    data class OnQueryChange(val query: String) : SearchEvents()
    data object OnSearch : SearchEvents()
    data class OnSearchFocusChange(val isFocused: Boolean) : SearchEvents()
}

sealed class SearchEffects {
    data object ResultSuccess : SearchEffects()
    data class ResultFailure(val message: UiText) : SearchEffects()
    data object AuthFailed : SearchEffects()
}