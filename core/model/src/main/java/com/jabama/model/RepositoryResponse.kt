package com.jabama.model

data class RepositoryResponse(
    val items: List<Repository>
)

data class Repository(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
)