package com.jabama.challenge.repository.token

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TokenRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val ioDispatcher: CoroutineDispatcher
) : TokenRepository {

    override suspend fun saveToken(token: String) {
        sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()
    }

    override fun readToken(): Flow<String> = flow {
        emit(
            sharedPreferences.getString(TOKEN, "") ?: ""
        )
    }.flowOn(ioDispatcher)

    companion object {
        private const val TOKEN = "TOKEN"
    }
}