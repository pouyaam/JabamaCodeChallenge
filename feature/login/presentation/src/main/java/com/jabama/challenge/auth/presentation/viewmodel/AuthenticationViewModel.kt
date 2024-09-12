package com.jabama.challenge.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.common.utils.UiText
import com.jabama.challenge.common.utils.toApiCallError
import com.jabama.challenge.token.domain.usecase.RefreshAccessToken
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val refreshAccessToken: RefreshAccessToken,
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
                            refreshAccessToken(code, state)
                            _effects.emit(AuthenticationEffects.ResultSuccess)
                        } catch (e: Exception) {
                            coroutineContext.ensureActive()
                            e.printStackTrace()
                            _effects.emit(AuthenticationEffects.ResultFailure(e.toApiCallError().message))
                        }
                    } ?: run {
                        _effects.emit(AuthenticationEffects.ResultFailure(UiText.DynamicString("orEmpty")))
                    }
                }
            }
        }
    }

}