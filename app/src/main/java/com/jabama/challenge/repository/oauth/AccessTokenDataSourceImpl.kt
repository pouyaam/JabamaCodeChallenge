package com.jabama.challenge.repository.oauth

import com.jabama.challenge.core.network.oauth.AccessTokenService
import com.jabama.challenge.core.network.oauth.RequestAccessToken

class AccessTokenDataSourceImpl(private val accessTokenService: AccessTokenService) : AccessTokenDataSource {
    override fun accessToken(requestAccessToken: RequestAccessToken) = accessTokenService.accessToken(requestAccessToken)
}