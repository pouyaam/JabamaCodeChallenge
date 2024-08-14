package com.jabama.challenge.repository.token

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class TokenRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val ioScope: CoroutineScope
) : TokenRepository {
    override fun saveToken(token: String): Deferred<Unit> =
        ioScope.async { sharedPreferences.edit().apply { putString(TOKEN, token) }.apply() }


    override fun readToken(): Deferred<String> =
        ioScope.async { sharedPreferences.getString(TOKEN, "") ?: "" }

    companion object {
        private const val TOKEN = "TOKEN"
    }
}