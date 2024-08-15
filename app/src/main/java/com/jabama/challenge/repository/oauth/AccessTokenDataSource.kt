package com.jabama.challenge.repository.oauth

import com.jabama.challenge.core.common.model.GeneralError
import com.jabama.challenge.core.common.model.Result
import com.jabama.challenge.core.network.oauth.AccessTokenBody
import com.jabama.challenge.data.model.AccessToken
import kotlinx.coroutines.flow.Flow

interface AccessTokenDataSource {
    fun accessToken(
        accessTokenBody: AccessTokenBody
    ): Flow<Result<AccessToken, GeneralError>>
}