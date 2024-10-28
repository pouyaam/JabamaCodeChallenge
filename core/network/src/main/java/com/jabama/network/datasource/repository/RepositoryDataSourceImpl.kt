package com.jabama.network.datasource.repository

import com.jabama.common.Resource
import com.jabama.network.api.SearchService
import com.jabama.network.model.RepositoryResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryDataSourceImpl(
    private val service: SearchService
) : RepositoryDataSource {

    override fun getRepositories(
        searchQuery: String,
        perPage: Int,
        page: Int,
    ): Flow<Resource<RepositoryResponseDto>> = flow {
        emit(service.getRepositories(searchQuery, perPage, page))
    }

}