package com.jabama.challenge.repository.token

import android.content.SharedPreferences
import com.jabama.challenge.core.common.model.GeneralError
import com.jabama.challenge.core.network.oauth.AccessTokenBody
import com.jabama.challenge.core.common.model.Result
import com.jabama.challenge.github.BuildConfig
import com.jabama.challenge.repository.oauth.AccessTokenDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class TokenRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val accessTokenDataSource: AccessTokenDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : TokenRepository {

    override suspend fun saveToken(token: String) {
        sharedPreferences.edit().apply { putString(TOKEN, token) }.apply()
    }

    override fun readToken(): Flow<String> = flow {
        emit(
            sharedPreferences.getString(TOKEN, "") ?: ""
        )
    }.flowOn(ioDispatcher)

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
        private const val TOKEN = "TOKEN"
        private const val STATE = "0"
    }
}