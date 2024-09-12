package com.jabama.challenge.token.data.repo

import android.content.SharedPreferences
import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.repo.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val accessTokenService: AccessTokenService
) : TokenRepository {
    private fun saveToken(token: String) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()
        }
    }

    override suspend fun refreshAccessToken(requestAccessToken: RequestAccessToken) {
        val response = accessTokenService.accessToken(requestAccessToken = requestAccessToken)
        saveToken(token = response.accessToken)
    }


    override fun readToken() =
        sharedPreferences.getString(TOKEN, "") ?: ""
}