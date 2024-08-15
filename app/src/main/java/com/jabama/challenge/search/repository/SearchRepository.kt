package com.jabama.challenge.search.repository

import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import com.jabama.challenge.search.model.RepoSearch
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchRepos(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<Result<RepoSearch, GeneralError>>
}