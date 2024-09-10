package com.jabama.challenge.token.domain.repo

import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.model.response.ResponseAccessToken

interface AccessTokenDataSource {
    suspend fun accessToken(requestAccessToken: RequestAccessToken): ResponseAccessToken
}