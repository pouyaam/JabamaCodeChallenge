package com.jabama.challenge.data.model

import com.jabama.challenge.core.network.oauth.AccessTokenResponse

data class AccessToken(
    var accessToken: String,
    var tokenType: String
)

fun AccessTokenResponse.asExternal() = AccessToken(
    accessToken = accessToken,
    tokenType = tokenType
)
