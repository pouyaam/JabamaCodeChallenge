package com.jabama.challenge.core.token.remote.datasource

import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import com.jabama.challenge.core.token.remote.model.AccessTokenBody
import com.jabama.challenge.core.token.AccessToken
import kotlinx.coroutines.flow.Flow

interface AccessTokenDataSource {
    fun accessToken(
        accessTokenBody: AccessTokenBody
    ): Flow<Result<AccessToken, GeneralError>>
}