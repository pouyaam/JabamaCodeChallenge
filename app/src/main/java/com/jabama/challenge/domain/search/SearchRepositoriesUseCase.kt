package com.jabama.challenge.domain.search

import com.jabama.challenge.data.search.model.GitHubResponse

interface SearchRepositoriesUseCase {
    suspend fun execute(query: String): GitHubResponse
}
