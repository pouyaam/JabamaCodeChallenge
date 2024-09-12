package com.jabama.challenge.token.domain.usecase

import com.jabama.challenge.token.domain.repo.TokenRepository

class ReadAccessToken(private val tokenRepository: TokenRepository) {
     operator fun invoke() = tokenRepository.readToken()
}