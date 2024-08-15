package com.jabama.challenge.core.prefs

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PreferencesDataSourceImpl(
    private val prefs: SharedPreferences,
    private val ioDispatcher: CoroutineDispatcher
) : PreferencesDataSource{
    override suspend fun saveToken(token: String) {
        prefs.edit().apply { putString(TOKEN, token) }.apply()
    }

    override fun readToken(): Flow<String> = flow {
        emit(
            prefs.getString(TOKEN, "") ?: ""
        )
    }.flowOn(ioDispatcher)

    companion object {
        private const val TOKEN = "TOKEN"
    }
}