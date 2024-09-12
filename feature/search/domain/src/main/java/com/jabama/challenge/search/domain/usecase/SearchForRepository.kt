package com.jabama.challenge.search.domain.usecase

import com.jabama.challenge.search.domain.model.SearchResult
import com.jabama.challenge.search.domain.repo.SearchRepository

class SearchForRepository(private val repository: SearchRepository) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 30
    ): Result<List<SearchResult>> {
        if(query.isBlank()) {
            return Result.success(emptyList())
        }
        return repository.searchRepos(query.trim(), page, pageSize)
    }
}