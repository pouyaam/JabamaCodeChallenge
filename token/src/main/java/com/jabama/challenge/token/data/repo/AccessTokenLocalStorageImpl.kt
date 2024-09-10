package com.jabama.challenge.token.data.repo

import android.content.SharedPreferences
import com.jabama.challenge.token.domain.repo.AccessTokenLocalStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

private const val TOKEN = "TOKEN"

class AccessTokenLocalStorageImpl(private val sharedPreferences: SharedPreferences) : AccessTokenLocalStorage {
    override fun saveToken(token: String): Deferred<Unit> =
        CoroutineScope(Dispatchers.IO).async { sharedPreferences.edit().apply { putString(TOKEN, token) }.apply() }


    override fun readToken(): Deferred<String> =
        CoroutineScope(Dispatchers.IO).async { sharedPreferences.getString(TOKEN, "") ?: "" }
}