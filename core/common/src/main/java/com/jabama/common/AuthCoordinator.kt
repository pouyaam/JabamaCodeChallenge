package com.jabama.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

object AuthCoordinator {
    private val _authCode = MutableSharedFlow<String>()
    val authCode: SharedFlow<String> = _authCode

    suspend fun emitAuthCode(code: String) {
        _authCode.emit(code)
    }
}