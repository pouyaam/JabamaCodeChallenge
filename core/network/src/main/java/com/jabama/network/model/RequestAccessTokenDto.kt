package com.jabama.network.model

import com.google.gson.annotations.SerializedName

data class RequestAccessTokenDto(
    @SerializedName("client_id")
    val clientId: String,

    @SerializedName("client_secret")
    val clientSecret: String,

    @SerializedName("code")
    val code: String,

    @SerializedName("redirect_uri")
    val redirectUri: String,

    @SerializedName("state")
    val state: String
)