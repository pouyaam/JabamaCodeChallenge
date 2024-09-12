package com.jabama.challenge.token.data.repo

import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.preferences.AbstractPreferences
import com.jabama.challenge.token.domain.repo.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TokenRepositoryImpl(
    private val preferences: AbstractPreferences,
    private val accessTokenService: AccessTokenService
) : TokenRepository {
    private fun saveToken(token: String) {
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
            preferences.saveToken(token)
        }
    }

    override suspend fun refreshAccessToken(requestAccessToken: RequestAccessToken) {
        val response = accessTokenService.accessToken(requestAccessToken = requestAccessToken)
        saveToken(token = response.accessToken)
    }


    override fun readToken() =
        preferences.readToken()
}