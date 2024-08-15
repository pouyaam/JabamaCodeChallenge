package com.jabama.challenge.search.model

import androidx.annotation.StringRes
import com.jabama.challenge.github.R
import kotlinx.collections.immutable.ImmutableList

data class SearchUiState(
    val state: PageState,
    val items: ImmutableList<Repo>,
    val page: Int,
    val totalCount: Int,
    val isLastPage: Boolean
)

sealed class PageState {
    data object Loading : PageState()
    data object NoItems : PageState()
    data class Error(val type: ErrorType) : PageState()
    data object Idle : PageState()
}

enum class ErrorType(@StringRes val textId: Int) {
    GENERAL(textId = R.string.general_error),
    SPAMMED(textId = R.string.spammed_error)
}