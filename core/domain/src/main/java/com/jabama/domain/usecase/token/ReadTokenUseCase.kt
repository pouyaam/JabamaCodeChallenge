package com.jabama.domain.usecase.token

import com.jabama.domain.repository.token.TokenRepository

class ReadTokenUseCase(
    private val repository: TokenRepository
) {

    operator fun invoke(): String {
        return repository.readToken()
    }

}