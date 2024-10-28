package com.jabama.network.api

import com.jabama.common.Resource
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login/oauth/access_token")
    suspend fun accessToken(
        @Body requestAccessToken: RequestAccessTokenDto
    ): Resource<ResponseAccessTokenDto>

}