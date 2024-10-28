package com.jabama.network.api

import com.jabama.common.Resource
import com.jabama.network.model.RepositoryResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") searchQuery: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
    ): Resource<RepositoryResponseDto>
}