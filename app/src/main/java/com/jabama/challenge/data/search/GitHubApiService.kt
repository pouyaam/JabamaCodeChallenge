package com.jabama.challenge.data.search

import com.jabama.challenge.data.search.model.GitHubResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String
    ): GitHubResponse
}
