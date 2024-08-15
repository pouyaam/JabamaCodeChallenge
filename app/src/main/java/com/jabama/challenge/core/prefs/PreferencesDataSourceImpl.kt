package com.jabama.challenge.core.prefs

import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PreferencesDataSourceImpl(
    private val prefs: SharedPreferences,
) : PreferencesDataSource{

    private val _token = MutableStateFlow(
        prefs.getString(TOKEN, EMPTY_TOKEN) ?: EMPTY_TOKEN
    )

    override val token = _token.asStateFlow()

    override suspend fun saveToken(token: String) {
        prefs.edit().apply { putString(TOKEN, token) }.apply()
        _token.emit(token)
    }

    override suspend fun invalidateToken() {
        saveToken(EMPTY_TOKEN)
    }

    companion object {
        private const val TOKEN = "TOKEN"
        private const val EMPTY_TOKEN = ""
    }
}
