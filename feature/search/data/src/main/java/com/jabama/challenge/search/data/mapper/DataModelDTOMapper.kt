package com.jabama.challenge.search.data.mapper

import com.jabama.challenge.search.data.model.SearchReposResponse
import com.jabama.challenge.search.domain.model.SearchResult

fun SearchReposResponse.mapToListOfSearchResult(): List<SearchResult> = items.map {
    SearchResult(
        id = it.id,
        name = it.name,
        private = it.private,
        ownerName = it.owner?.login,
        ownerAvatarUrl = it.owner?.avatarUrl,
        description = it.description,
        stargazersCount = it.stargazersCount,
        language = it.language,
    )
}