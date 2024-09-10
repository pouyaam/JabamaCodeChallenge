package com.jabama.challenge.token.data.repo

import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.repo.AccessTokenDataSource

class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) :
    AccessTokenDataSource {
    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenService.accessToken(requestAccessToken = requestAccessToken)
}