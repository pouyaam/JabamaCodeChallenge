package com.jabama.challenge.search.domain.model

data class SearchResult(
    val id: Long,
    val name: String?,
    val private: Boolean,
    val ownerName: String?,
    val ownerAvatarUrl: String?,
    val description: String?,
    val stargazersCount: Int,
    var languageColor: Int = 0xFFFFFFFF.toInt(),
    val language: String?,
)
