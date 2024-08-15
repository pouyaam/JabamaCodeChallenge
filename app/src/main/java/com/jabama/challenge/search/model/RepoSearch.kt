package com.jabama.challenge.search.model

import com.jabama.challenge.search.remote.RepoSearchResponse

data class RepoSearch(
    val totalCount: Int,
    val items: List<Repo>
)

fun RepoSearchResponse.asExternal() = RepoSearch(
    totalCount = totalCount,
    items = items.map { it.asExternal() }
)
