package com.jabama.data.mapper

import com.jabama.model.Repository
import com.jabama.model.RepositoryResponse
import com.jabama.model.RequestAccessToken
import com.jabama.model.ResponseAccessToken
import com.jabama.network.model.RepositoryDto
import com.jabama.network.model.RepositoryResponseDto
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto


fun RequestAccessToken.toDataModel(): RequestAccessTokenDto {
    return RequestAccessTokenDto(
        clientId = clientId,
        clientSecret = clientSecret,
        code = code,
        redirectUri = redirectUri,
        state = state
    )
}

fun ResponseAccessTokenDto.toDomainModel(): ResponseAccessToken {
    return ResponseAccessToken(
        accessToken = accessToken,
        tokenType = tokenType
    )
}

fun RepositoryResponseDto.toDomainModel(): RepositoryResponse {
    return RepositoryResponse(
        items = items.map { it.toDomainModel() }
    )
}

fun RepositoryDto.toDomainModel(): Repository {
    return Repository(
        id = id,
        name = name,
        description = description,
        language = language
    )
}