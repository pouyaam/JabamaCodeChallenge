package com.jabama.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.common.AuthCoordinator
import com.jabama.common.Resource
import com.jabama.domain.usecase.aouth.GetAccessTokenUseCase
import com.jabama.domain.usecase.token.SaveTokenUseCase
import com.jabama.feature.login.BuildConfig
import com.jabama.model.RequestAccessToken
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(
    private val accessTokenUseCase: GetAccessTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("LoginViewModel", "Caught exception: $throwable")
    }

    init {
        viewModelScope.launch {
            AuthCoordinator.authCode.collect { code ->
                onEvent(LoginEvent.AuthorizationCodeReceived(code))
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        viewModelScope.launch {
            when (event) {
                LoginEvent.AuthorizeClicked -> initiateAuthorization()
                is LoginEvent.AuthorizationCodeReceived -> handleAuthorizationCode(event.code)
            }
        }
    }

    private fun getAuthorizationUrl(): String {
        return "https://github.com/login/oauth/authorize?client_id=${BuildConfig.GITHUB_CLIENT_ID}&redirect_uri=${BuildConfig.GITHUB_CALLBACK_URL}&scope=repo user&state=0"
    }

    private suspend fun initiateAuthorization() {
        _effect.send(LoginEffect.NavigateToAuthUrl(getAuthorizationUrl()))
    }

    private fun handleAuthorizationCode(code: String) {
        Log.d("LoginViewModel", "Received authorization code: $code")
        getToken(code)
    }

    private fun getToken(code: String) {
        viewModelScope.launch(exceptionHandler) {
            _state.update { it.copy(authorizeButtonIsLoading = true) }

            accessTokenUseCase(
                RequestAccessToken(
                    clientId = BuildConfig.GITHUB_CLIENT_ID,
                    clientSecret = BuildConfig.GITHUB_CLIENT_SECRET,
                    code = code,
                    redirectUri = BuildConfig.GITHUB_CALLBACK_URL,
                    state = "0"
                )
            ).let { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update { it.copy(authorizeButtonIsLoading = false, isFailed = false) }
                        saveToken(result.data.accessToken)
                        navigateToSearch()
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(authorizeButtonIsLoading = false, isFailed = true) }
                    }

                }
            }


        }
    }

    private suspend fun navigateToSearch() {
        _effect.send(LoginEffect.NavigateToSearch)
    }

    private fun saveToken(token: String) {
        viewModelScope.launch {
            saveTokenUseCase.invoke(token)
        }
    }
}
