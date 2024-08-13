package com.jabama.challenge.repository.oauth

import com.jabama.challenge.core.network.oauth.RequestAccessToken
import com.jabama.challenge.core.network.oauth.ResponseAccessToken
import kotlinx.coroutines.Deferred

interface AccessTokenDataSource {
    fun accessToken(requestAccessToken: RequestAccessToken): Deferred<ResponseAccessToken>
}