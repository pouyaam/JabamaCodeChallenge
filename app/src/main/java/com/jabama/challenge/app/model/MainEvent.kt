package com.jabama.challenge.app.model

sealed class MainEvent {
    data object NavigateToLoginPage : MainEvent()
    data object NavigateToSearchPage : MainEvent()
}
