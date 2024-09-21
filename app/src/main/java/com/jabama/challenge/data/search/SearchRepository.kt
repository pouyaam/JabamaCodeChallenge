package com.jabama.challenge.data.search

import com.jabama.challenge.data.search.model.GitHubResponse

interface SearchRepository {
    suspend fun searchRepositories(query: String): GitHubResponse
}