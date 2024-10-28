package com.jabama.challenge.authentication

data class AuthenticationState(
    val isAuthenticated: Boolean = false,
    val chooseDestinationLoading: Boolean = true,
)

sealed interface AuthenticationEvent {
    data object CheckAuthStatus : AuthenticationEvent
}