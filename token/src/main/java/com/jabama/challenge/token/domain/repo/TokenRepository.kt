package com.jabama.challenge.token.domain.repo

import com.jabama.challenge.token.domain.model.request.RequestAccessToken

interface TokenRepository {
    suspend fun refreshAccessToken(requestAccessToken: RequestAccessToken)
    fun readToken(): String
}