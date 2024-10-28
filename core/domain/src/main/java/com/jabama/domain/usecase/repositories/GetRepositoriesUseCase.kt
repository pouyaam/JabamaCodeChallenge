package com.jabama.domain.usecase.repositories

import com.jabama.common.Resource
import com.jabama.domain.repository.repositories.RepositoriesSearchRepository
import com.jabama.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUseCase(
    private val repository: RepositoriesSearchRepository
) {

    operator fun invoke(
        searchQuery: String,
        perPage: Int,
        page: Int,
    ): Flow<Resource<RepositoryResponse>> {
        return repository.getRepositories(searchQuery = searchQuery, perPage = perPage, page = page)
    }

}