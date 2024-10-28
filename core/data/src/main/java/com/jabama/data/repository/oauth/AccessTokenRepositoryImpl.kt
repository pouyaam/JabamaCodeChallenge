package com.jabama.data.repository.oauth

import com.jabama.common.Resource
import com.jabama.data.mapper.toDataModel
import com.jabama.data.mapper.toDomainModel
import com.jabama.domain.repository.oauth.AccessTokenRepository
import com.jabama.model.RequestAccessToken
import com.jabama.model.ResponseAccessToken
import com.jabama.network.datasource.token.AccessTokenDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AccessTokenRepositoryImpl(
    private val dataSource: AccessTokenDataSource,
    private val defaultDispatcher: CoroutineDispatcher
) : AccessTokenRepository {

    override suspend fun accessToken(
        requestAccessToken: RequestAccessToken
    ): Resource<ResponseAccessToken> {
        val mappedData = withContext(defaultDispatcher) {
            requestAccessToken.toDataModel()
        }
        return dataSource.accessToken(mappedData).let {
            when (it) {
                is Resource.Error -> Resource.Error(exception = it.exception)
                is Resource.Success -> {
                    val mappedResult = withContext(defaultDispatcher) {
                        it.data.toDomainModel()
                    }
                    Resource.Success(data = mappedResult)
                }
            }
        }
    }

}