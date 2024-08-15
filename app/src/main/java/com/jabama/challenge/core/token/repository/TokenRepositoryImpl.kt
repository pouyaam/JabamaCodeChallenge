package com.jabama.challenge.core.token.repository

import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import com.jabama.challenge.core.prefs.PreferencesDataSource
import com.jabama.challenge.core.token.remote.model.AccessTokenBody
import com.jabama.challenge.core.token.remote.datasource.AccessTokenDataSource
import com.jabama.challenge.github.BuildConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(
    private val preferencesDataSource: PreferencesDataSource,
    private val accessTokenDataSource: AccessTokenDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : TokenRepository {

    override val token: StateFlow<String> = preferencesDataSource.token

    override suspend fun saveToken(token: String)  =
        preferencesDataSource.saveToken(token)

    override suspend fun invalidate() =
        preferencesDataSource.invalidateToken()

    override fun fetchAccessToken(authorizeCode: String): Flow<Result<Unit, GeneralError>> =
        accessTokenDataSource.accessToken(
            accessTokenBody = AccessTokenBody(
                clientId = BuildConfig.CLIENT_ID,
                clientSecret = BuildConfig.CLIENT_SECRET,
                code = authorizeCode,
                redirectUri = BuildConfig.REDIRECT_URI,
                state = STATE
            )
        ).map { result ->
            when (result) {
                is Result.Failure -> Result.Failure(result.error)

                is Result.Success -> {
                    saveToken(result.data.accessToken)
                    Result.Success(Unit)
                }
            }
        }.flowOn(ioDispatcher)

    companion object {
        private const val STATE = "0"
    }
}