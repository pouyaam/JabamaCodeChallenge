package com.jabama.challenge.core.token.remote.datasource

import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import com.jabama.challenge.core.network.adapter.NetworkResponse
import com.jabama.challenge.core.token.remote.model.AccessTokenBody
import com.jabama.challenge.core.token.remote.AccessTokenService
import com.jabama.challenge.core.token.AccessToken
import com.jabama.challenge.core.token.asExternal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccessTokenDataSourceImpl(
    private val accessTokenService: AccessTokenService
) : AccessTokenDataSource {

    override fun accessToken(
        accessTokenBody: AccessTokenBody
    ): Flow<Result<AccessToken, GeneralError>> = flow {

        val result = when (val response = accessTokenService.accessToken(accessTokenBody)) {
            is NetworkResponse.ApiError ->
                Result.Failure(GeneralError.ApiError(code = response.code, message = response.body.toString()))

            is NetworkResponse.NetworkError ->
                Result.Failure(GeneralError.NetworkError)

            is NetworkResponse.UnknownError ->
                Result.Failure(GeneralError.UnknownError(error = response.error))

            is NetworkResponse.Success -> {
                response.body?.let { data ->
                    Result.Success(data.asExternal())
                } ?: Result.Failure(GeneralError.UnknownError(error = Exception("The response body is null!")))
            }
        }

        emit(result)
    }
}