package com.jabama.challenge.core.token.repository

import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveToken(token: String)
    fun readToken(): Flow<String>
    fun fetchAccessToken(authorizeCode: String): Flow<Result<Unit, GeneralError>>
}