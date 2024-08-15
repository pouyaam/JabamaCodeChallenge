package com.jabama.challenge.core.prefs

import kotlinx.coroutines.flow.StateFlow

interface PreferencesDataSource {
    val token: StateFlow<String>

    suspend fun saveToken(token: String)
    suspend fun invalidateToken()
}
