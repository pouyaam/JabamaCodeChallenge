package com.jabama.network.datasource.token

import com.jabama.common.Resource
import com.jabama.network.api.AuthService
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto

class AccessTokenDataSourceImpl(
    private val service: AuthService
) : AccessTokenDataSource {

    override suspend fun accessToken(
        requestAccessToken: RequestAccessTokenDto
    ): Resource<ResponseAccessTokenDto> = service.accessToken(requestAccessToken)

}