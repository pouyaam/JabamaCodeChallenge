package com.jabama.challenge.token.data.api

import com.jabama.challenge.common.constants.GITHUB_ACCESS_TOKEN_URL
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.model.response.ResponseAccessToken
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface AccessTokenService {
    @Headers("Accept:application/json")
    @POST
    suspend fun accessToken(
        @Url url: String = GITHUB_ACCESS_TOKEN_URL,
        @Body requestAccessToken: RequestAccessToken
    ): ResponseAccessToken
}