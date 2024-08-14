package com.jabama.challenge.core.network.oauth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestAccessToken(
    @SerialName("client_id")
    var clientId: String,
    @SerialName("client_secret")
    var clientSecret: String,
    var code: String,
    @SerialName("redirect_uri")
    var redirectUri: String,
    var state: String
)
