package com.jabama.challenge.authentication

import androidx.lifecycle.ViewModel
import com.jabama.domain.usecase.token.ReadTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthenticationViewModel(
    private val readTokenUseCase: ReadTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthenticationState())
    val state = _state.asStateFlow()


    fun onEvent(event: AuthenticationEvent) {
        when (event) {
            AuthenticationEvent.CheckAuthStatus -> checkAuthStatus()
        }
    }

    private fun checkAuthStatus() {
        val isLoggedIn = readTokenUseCase().isNotEmpty()
        _state.update { it.copy(
            isAuthenticated = isLoggedIn,
            chooseDestinationLoading = false
        ) }
    }

}