package com.jabama.challenge.domain.search

import com.jabama.challenge.data.search.SearchRepository
import com.jabama.challenge.data.search.model.GitHubResponse

class SearchRepositoriesUseCaseImpl(private val authRepository: SearchRepository) :
    SearchRepositoriesUseCase {
    override suspend fun execute(query: String): GitHubResponse {
        return authRepository.searchRepositories(query)
    }
}
