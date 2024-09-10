package com.jabama.challenge.auth.presentation.viewmodel

import com.jabama.challenge.auth.presentation.model.WebLoginResponse
import com.jabama.challenge.common.utils.UiText

sealed class AuthenticationEvents {
    data class RefreshToken(val webLoginResponse: WebLoginResponse) : AuthenticationEvents()
}

sealed class AuthenticationEffects {
    data object ResultSuccess : AuthenticationEffects()
    data class ResultFailure(val message: UiText) : AuthenticationEffects()
}