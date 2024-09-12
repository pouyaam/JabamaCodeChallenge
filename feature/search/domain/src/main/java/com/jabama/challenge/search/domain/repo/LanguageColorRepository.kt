package com.jabama.challenge.search.domain.repo

interface LanguageColorRepository {
    suspend fun getLanguageColor(language: String): Int
}