package com.jabama.challenge.token.fakes

import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.preferences.AbstractPreferences
import com.jabama.challenge.token.domain.repo.TokenRepository

class FakeTokenRepository(
    private val preferences: AbstractPreferences,
    private val accessTokenService: AccessTokenService
) : TokenRepository {
    private fun saveToken(token: String) {
        preferences.saveToken(token)
    }

    override suspend fun refreshAccessToken(requestAccessToken: RequestAccessToken) {
        val response = accessTokenService.accessToken(requestAccessToken = requestAccessToken)
        saveToken(token = response.accessToken)
    }


    override fun readToken() =
        preferences.readToken()
}