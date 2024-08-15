package com.jabama.challenge.search.remote

import kotlinx.serialization.Serializable

@Serializable
data class RepoResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val score: Float,
    val language: String?
)