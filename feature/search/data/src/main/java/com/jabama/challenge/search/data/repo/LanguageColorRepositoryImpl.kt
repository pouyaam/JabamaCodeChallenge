package com.jabama.challenge.search.data.repo

import android.content.Context
import android.graphics.Color
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jabama.challenge.search.data.R
import com.jabama.challenge.search.domain.repo.LanguageColorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class LanguageColorRepositoryImpl(private val context: Context) : LanguageColorRepository {
    override suspend fun getLanguageColor(language: String): Int {
        return try {
            withContext(SupervisorJob() + Dispatchers.IO) {
                context.resources.openRawResource(R.raw.languages_colors)
                    .bufferedReader().use {
                        val jsonText = it.readText()
                        val gson = Gson()
                        val mapType = object : TypeToken<Map<String, Map<String, String>>>() {}.type
                        val languageColors =
                            gson.fromJson<Map<String, Map<String, String>>>(jsonText, mapType)
                        val languageString = languageColors[language]?.get("color")
                        Color.parseColor(languageString)
                    }
            }
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            e.printStackTrace()
            Color.WHITE
        }
    }
}