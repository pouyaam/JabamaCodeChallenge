package com.jabama.network.model


data class RepositoryResponseDto(
    val items: List<RepositoryDto>
)

data class RepositoryDto(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
)