package com.jabama.challenge.core.token

import com.jabama.challenge.core.token.remote.model.AccessTokenResponse

data class AccessToken(
    var accessToken: String,
    var tokenType: String
)

fun AccessTokenResponse.asExternal() = AccessToken(
    accessToken = accessToken,
    tokenType = tokenType
)
