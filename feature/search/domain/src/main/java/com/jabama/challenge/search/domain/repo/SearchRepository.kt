package com.jabama.challenge.search.domain.repo

import com.jabama.challenge.search.domain.model.SearchResult

interface SearchRepository {
    suspend fun searchRepos(query: String, page: Int, pageSize: Int): Result<List<SearchResult>>
}