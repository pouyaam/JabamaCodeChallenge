package com.jabama.network.datasource.repository

import com.jabama.common.Resource
import com.jabama.network.model.RepositoryResponseDto
import kotlinx.coroutines.flow.Flow

interface RepositoryDataSource {

    fun getRepositories(
        searchQuery: String,
        perPage: Int,
        page: Int,
    ): Flow<Resource<RepositoryResponseDto>>

}