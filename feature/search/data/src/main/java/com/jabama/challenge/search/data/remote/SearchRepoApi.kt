package com.jabama.challenge.search.data.remote

import com.jabama.challenge.search.data.model.SearchReposResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchRepoApi {

    @Headers(
        "Accept:application/vnd.github+json",
        "X-GitHub-Api-Version:2022-11-28",
    )
    @GET("search/repositories")
    suspend fun searchRepo(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): SearchReposResponse

}