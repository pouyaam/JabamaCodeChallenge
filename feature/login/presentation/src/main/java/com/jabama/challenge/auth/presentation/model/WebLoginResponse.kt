package com.jabama.challenge.auth.presentation.model

data class WebLoginResponse(
    val code: String = "",
    val state: String = "",
)
