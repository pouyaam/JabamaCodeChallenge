package com.jabama.network.datasource.token

import com.jabama.common.Resource
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto

interface AccessTokenDataSource {

    suspend fun accessToken(
        requestAccessToken: RequestAccessTokenDto
    ): Resource<ResponseAccessTokenDto>

}