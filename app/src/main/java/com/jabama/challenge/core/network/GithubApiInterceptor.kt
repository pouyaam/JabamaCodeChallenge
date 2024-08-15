package com.jabama.challenge.core.network

import com.jabama.challenge.core.token.repository.TokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class GithubApiInterceptor(
    private val tokenRepository: TokenRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${tokenRepository.token.value}")
            .build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            runBlocking {
                tokenRepository.invalidate()
            }
        }

        return response
    }
}
