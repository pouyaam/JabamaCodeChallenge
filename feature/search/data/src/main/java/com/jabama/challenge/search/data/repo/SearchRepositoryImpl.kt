package com.jabama.challenge.search.data.repo

import com.jabama.challenge.search.data.mapper.mapToListOfSearchResult
import com.jabama.challenge.search.data.remote.SearchRepoApi
import com.jabama.challenge.search.domain.model.SearchResult
import com.jabama.challenge.search.domain.repo.SearchRepository
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

class SearchRepositoryImpl(
    private val api: SearchRepoApi
) : SearchRepository {
    override suspend fun searchRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<SearchResult>> {
        return try {
            val response = api.searchRepo(query, page, pageSize)
            return Result.success(response.mapToListOfSearchResult())
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            e.printStackTrace()
            return Result.failure(e)
        }
    }

}