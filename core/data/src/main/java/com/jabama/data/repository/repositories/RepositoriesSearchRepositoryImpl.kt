package com.jabama.data.repository.repositories

import com.jabama.common.Resource
import com.jabama.data.mapper.toDomainModel
import com.jabama.domain.repository.repositories.RepositoriesSearchRepository
import com.jabama.model.RepositoryResponse
import com.jabama.network.datasource.repository.RepositoryDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RepositoriesSearchRepositoryImpl(
    private val dataSource: RepositoryDataSource,
    private val defaultDispatcher: CoroutineDispatcher
) : RepositoriesSearchRepository {

    override fun getRepositories(
        searchQuery: String,
        perPage: Int,
        page: Int,
    ): Flow<Resource<RepositoryResponse>> {
        return dataSource.getRepositories(searchQuery, perPage, page).map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val mappedData = withContext(defaultDispatcher) {
                        resource.data.toDomainModel()
                    }

                    val limitedResponse = mappedData.copy(
                        items = mappedData.items.take(30)
                    )

                    Resource.Success(limitedResponse)
                }

                is Resource.Error -> Resource.Error(resource.exception)
            }
        }
    }
}