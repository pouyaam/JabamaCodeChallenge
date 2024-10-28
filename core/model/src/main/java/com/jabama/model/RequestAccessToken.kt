package com.jabama.model

data class RequestAccessToken(
    val clientId: String,
    val clientSecret: String,
    val code: String,
    val redirectUri: String,
    val state: String
)