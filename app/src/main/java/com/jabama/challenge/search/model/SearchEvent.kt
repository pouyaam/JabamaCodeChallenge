package com.jabama.challenge.search.model

sealed class SearchEvent {
    data object NavigateToLoginPage : SearchEvent()
}