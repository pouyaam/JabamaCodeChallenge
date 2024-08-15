package com.jabama.challenge.core.prefs

import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    suspend fun saveToken(token: String)
    fun readToken(): Flow<String>
}
