package com.jabama.challenge.core.network

import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(NetworkConstants.Header.accept.first, NetworkConstants.Header.accept.second)
            .build()
        return chain.proceed(request)
    }
}
