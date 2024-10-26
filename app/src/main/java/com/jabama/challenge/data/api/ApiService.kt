package com.jabama.challenge.data.api

import com.jabama.challenge.data.model.RepositoryDto
import com.jabama.challenge.data.model.RequestAccessTokenDto
import com.jabama.challenge.data.model.ResponseAccessTokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    suspend fun accessToken(@Body requestAccessTokenDto: RequestAccessTokenDto): ResponseAccessTokenDto

    @Headers("Accept: application/json")
    @GET("user/repos")
    suspend fun getUserRepositories(
        @Header("Authorization") token: String,
        @Query("type") type: String = "owner"
    ): List<RepositoryDto>
}