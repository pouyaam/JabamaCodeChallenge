package com.jabama.challenge.data.api

import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.data.model.ResponseAccessTokenDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface AccessTokenApiService {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    suspend fun accessToken(@Body requestAccessTokenDto: RequestAccessTokenDto): ResponseAccessTokenDto
}
