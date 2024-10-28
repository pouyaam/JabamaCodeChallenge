package com.jabama.domain.usecase.token

import com.jabama.domain.repository.token.TokenRepository

class SaveTokenUseCase(
    private val repository: TokenRepository
) {

    operator fun invoke(token: String) {
        repository.saveToken(token)
    }

}