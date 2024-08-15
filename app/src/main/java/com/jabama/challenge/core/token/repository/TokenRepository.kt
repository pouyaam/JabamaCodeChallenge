package com.jabama.challenge.core.token.repository

import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface TokenRepository {
    val token: StateFlow<String>

    suspend fun saveToken(token: String)

    suspend fun invalidate()

    fun fetchAccessToken(authorizeCode: String): Flow<Result<Unit, GeneralError>>
}