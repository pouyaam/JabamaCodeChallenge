package com.jabama.challenge.repository.token

import com.jabama.challenge.core.common.model.GeneralError
import com.jabama.challenge.core.common.model.Result
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun saveToken(token: String)
    fun readToken(): Flow<String>
    fun fetchAccessToken(authorizeCode: String): Flow<Result<Unit, GeneralError>>
}