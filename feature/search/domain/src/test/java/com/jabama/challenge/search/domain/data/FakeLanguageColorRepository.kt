package com.jabama.challenge.search.domain.data

import android.content.res.Resources.NotFoundException
import com.jabama.challenge.search.domain.repo.LanguageColorRepository

class FakeLanguageColorRepository : LanguageColorRepository {
    val languageColors = hashMapOf(
        "kotlin" to 0x00A97BFF,
        "java" to 0x00b07219,
        "dart" to 0x0000B4AB,
        "groovy" to 0x004298b8,
        "json" to 0x00292929,
    )

    override suspend fun getLanguageColor(language: String): Int {
        languageColors.get(language)?.let {
            return it
        } ?: run {
            throw NotFoundException()
        }
    }
}