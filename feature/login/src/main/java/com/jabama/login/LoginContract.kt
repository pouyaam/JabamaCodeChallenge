package com.jabama.login

data class LoginState(
    val authorizeButtonIsLoading: Boolean = false,
    val isFailed: Boolean = false,
)

sealed interface LoginEvent {
    data object AuthorizeClicked : LoginEvent
    data class AuthorizationCodeReceived(val code: String) : LoginEvent
}

sealed interface LoginEffect {
    data object NavigateToSearch : LoginEffect
    data class NavigateToAuthUrl(val url: String) : LoginEffect
}