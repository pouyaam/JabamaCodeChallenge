package com.jabama.challenge.search.remote
import com.jabama.challenge.core.network.NetworkConstants
import com.jabama.challenge.core.network.adapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET(NetworkConstants.GithubAPI.SEARCH_REPOSITORIES)
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): NetworkResponse<RepoSearchResponse, Unit>
}