package com.jabama.challenge.core.network.oauth

import com.jabama.challenge.core.network.NetworkConstants
import com.jabama.challenge.core.network.adapter.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AccessTokenService {
    @POST(NetworkConstants.OAuth.ACCESS_TOKEN)
    suspend fun accessToken(
        @Body accessTokenBody: AccessTokenBody
    ) : NetworkResponse<AccessTokenResponse, Unit>
}