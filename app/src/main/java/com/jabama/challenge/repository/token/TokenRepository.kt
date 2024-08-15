package com.jabama.challenge.repository.token

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveToken(token: String)
    fun readToken(): Flow<String>
}