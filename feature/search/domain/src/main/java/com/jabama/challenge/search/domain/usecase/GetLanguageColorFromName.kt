package com.jabama.challenge.search.domain.usecase

import com.jabama.challenge.search.domain.repo.LanguageColorRepository

class GetLanguageColorFromName(private val repository: LanguageColorRepository) {
    suspend operator fun invoke(language: String): Int {
        return repository.getLanguageColor(language)
    }
}