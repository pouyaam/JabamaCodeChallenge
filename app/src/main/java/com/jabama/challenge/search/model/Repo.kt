package com.jabama.challenge.search.model

import com.jabama.challenge.search.remote.RepoResponse

data class Repo(
    val id: Long,
    val name: String,
    val description: String?,
    val score: Float,
    val language: String?
)

fun RepoResponse.asExternal() = Repo(
    id = id,
    name = name,
    description = description,
    score = score,
    language = language
)