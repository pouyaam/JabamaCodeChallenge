package com.jabama.challenge.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.common.constants.CLIENT_ID
import com.jabama.challenge.common.constants.CLIENT_SECRET
import com.jabama.challenge.common.constants.REDIRECT_URI
import com.jabama.challenge.common.utils.UiText
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.repo.AccessTokenDataSource
import com.jabama.challenge.token.domain.repo.AccessTokenLocalStorage
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val tokenRepository: AccessTokenLocalStorage,
    private val accessTokenDataSource: AccessTokenDataSource,
) : ViewModel() {

    private val _effects = MutableSharedFlow<AuthenticationEffects>()
    val effects = _effects.asSharedFlow()

    fun invokeEvent(event: AuthenticationEvents) {
        when (event) {
            is AuthenticationEvents.RefreshToken -> {
                val code = event.webLoginResponse.code
                val state = event.webLoginResponse.state
                viewModelScope.launch {
                    code.takeIf { it.isNotEmpty() }?.let { code ->
                        try {
                            val response = accessTokenDataSource.accessToken(
                                RequestAccessToken(
                                    CLIENT_ID,
                                    CLIENT_SECRET,
                                    code,
                                    REDIRECT_URI,
                                    state
                                )
                            )
                            tokenRepository.saveToken(response.accessToken).await()
                            _effects.emit(AuthenticationEffects.ResultSuccess)
                        } catch (e: Exception) {
                            coroutineContext.ensureActive()
                            //TODO we should handle exception scenarios later
                            e.printStackTrace()
                            _effects.emit(AuthenticationEffects.ResultFailure(UiText.DynamicString(e.message.orEmpty())))
                        }
                    } ?: run {
                        _effects.emit(AuthenticationEffects.ResultFailure(UiText.DynamicString("orEmpty")))
                    }
                }
            }
        }
    }

}