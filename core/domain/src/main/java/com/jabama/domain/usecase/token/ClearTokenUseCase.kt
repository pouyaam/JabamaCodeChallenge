package com.jabama.domain.usecase.token

import com.jabama.domain.repository.token.TokenRepository

class ClearTokenUseCase(
    private val repository: TokenRepository
) {

    operator fun invoke() {
        return repository.clearToken()
    }

}