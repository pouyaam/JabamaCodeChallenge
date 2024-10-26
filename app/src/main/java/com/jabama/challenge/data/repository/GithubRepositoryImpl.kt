package com.jabama.challenge.data.repository

import com.jabama.challenge.data.api.AccessTokenApiService
import com.jabama.challenge.data.api.RepositoryApiService
import com.jabama.challenge.data.mapper.mapToRepository
import com.jabama.challenge.data.mapper.mapToRequestAccessTokenDto
import com.jabama.challenge.data.mapper.mapToResponseAccessToken
import com.jabama.challenge.domain.IGithubRepository
import com.jabama.challenge.domain.model.Repository
import com.jabama.challenge.domain.model.RequestAccessToken

class GithubRepositoryImpl(
    private val accessTokenApiService: AccessTokenApiService,
    private val repositoryApiService: RepositoryApiService,
) :
    IGithubRepository {
    override suspend fun accessToken(requestAccessToken: RequestAccessToken) =
        accessTokenApiService.accessToken(requestAccessToken.mapToRequestAccessTokenDto())
            .mapToResponseAccessToken()

    override suspend fun getRepositoryList(token: String): List<Repository> {
        return repositoryApiService.getUserRepositories(token = "Bearer $token")
            .map { it.mapToRepository() }
    }
}