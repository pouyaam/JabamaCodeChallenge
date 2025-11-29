package com.jabama.challenge.data.api

import com.jabama.challenge.data.model.RepositoryDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface RepositoryApiService {
    @Headers("Accept: application/json")
    @GET("user/repos")
    suspend fun getUserRepositories(
        @Header("Authorization") token: String,
        @Query("type") type: String = "owner"
    ): List<RepositoryDto>
}