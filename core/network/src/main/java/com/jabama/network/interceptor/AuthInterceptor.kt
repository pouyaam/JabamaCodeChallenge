package com.jabama.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(modifiedRequest)
    }
}