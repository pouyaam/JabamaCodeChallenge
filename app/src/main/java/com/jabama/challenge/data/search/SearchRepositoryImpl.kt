package com.jabama.challenge.data.search

import com.jabama.challenge.data.search.model.GitHubResponse

class SearchRepositoryImpl(private val apiService: GitHubApiService) : SearchRepository {
    override suspend fun searchRepositories(query: String): GitHubResponse =
        apiService.searchRepositories(query)
}
