package com.jabama.domain.repository.oauth

import com.jabama.common.Resource
import com.jabama.model.RequestAccessToken
import com.jabama.model.ResponseAccessToken

interface AccessTokenRepository {

    suspend fun accessToken(
        requestAccessToken: RequestAccessToken
    ): Resource<ResponseAccessToken>

}