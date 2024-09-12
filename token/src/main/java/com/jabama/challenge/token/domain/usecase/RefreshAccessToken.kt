package com.jabama.challenge.token.domain.usecase

import com.jabama.challenge.common.constants.CLIENT_ID
import com.jabama.challenge.common.constants.CLIENT_SECRET
import com.jabama.challenge.common.constants.REDIRECT_URI
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.repo.TokenRepository

class RefreshAccessToken(private val tokenRepository: TokenRepository) {
    suspend operator fun invoke(code: String, state: String) {
        tokenRepository.refreshAccessToken(
            RequestAccessToken(
                CLIENT_ID,
                CLIENT_SECRET,
                code,
                REDIRECT_URI,
                state
            )
        )
    }
}