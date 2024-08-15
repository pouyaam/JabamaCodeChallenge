package com.jabama.challenge.core.token.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokenResponse(
    @SerialName("access_token")
    var accessToken: String,
    @SerialName("token_type")
    var tokenType: String
)