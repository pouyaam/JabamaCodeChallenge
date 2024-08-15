package com.jabama.challenge.core.network.oauth

import com.jabama.challenge.core.network.adapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccessTokenService {
    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    suspend fun accessToken(
        @Body accessTokenBody: AccessTokenBody
    ) : NetworkResponse<AccessTokenResponse, Unit>
}