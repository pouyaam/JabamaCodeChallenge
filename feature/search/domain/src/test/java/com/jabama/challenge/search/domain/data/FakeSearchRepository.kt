package com.jabama.challenge.search.domain.data

import com.jabama.challenge.search.domain.model.SearchResult
import com.jabama.challenge.search.domain.repo.SearchRepository

class FakeSearchRepository(val requestSuccess: Boolean) : SearchRepository {
    override suspend fun searchRepos(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<SearchResult>> {
        return if (requestSuccess) {
            Result.success(
                mutableListOf<SearchResult>().apply {
                    for (i in 0..10) {
                        add(
                            SearchResult(
                                id = i.toLong(),
                                name = "my repository",
                                private = false,
                                ownerName = "reza zarchi",
                                ownerAvatarUrl = null,
                                description = "this is my new repository!",
                                stargazersCount = 102,
                                language = "Kotlin"
                            )
                        )
                    }
                }
            )
        } else {
            Result.failure<List<SearchResult>>(Exception("message"))
        }
    }
}