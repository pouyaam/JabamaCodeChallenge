package com.jabama.challenge.core.network.di

import com.jabama.challenge.core.network.GithubApiInterceptor
import com.jabama.challenge.core.network.NetworkConstants
import com.jabama.challenge.core.network.OAuthInterceptor
import com.jabama.challenge.core.network.adapter.NetworkCallAdapterFactory
import com.jabama.challenge.github.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

private enum class InterceptorType {
    LOGGING, OAUTH, GITHUB_API
}

private enum class OkHttpClientType {
    OAUTH, GITHUB_API
}

val networkModule = module {

    factory<Interceptor>(named(InterceptorType.LOGGING)) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }

    factory<Interceptor>(named(InterceptorType.OAUTH)) { OAuthInterceptor() }

    single<Interceptor>(named(InterceptorType.GITHUB_API)) { GithubApiInterceptor(get()) }

    factory<CallAdapter.Factory> { NetworkCallAdapterFactory() }

    factory<Json> { Json { ignoreUnknownKeys = true } }

    factory<Converter.Factory> { get<Json>().asConverterFactory(NetworkConstants.MEDIA_TYPE) }

    single<OkHttpClient>(named(OkHttpClientType.OAUTH)) {
        OkHttpClient.Builder()
            .readTimeout(NetworkConstants.TimeOut.READ, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TimeOut.WRITE, TimeUnit.SECONDS)
            .connectTimeout(NetworkConstants.TimeOut.CONNECTION, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>(named(InterceptorType.LOGGING)))
            .addInterceptor(get<Interceptor>(named(InterceptorType.OAUTH)))
            .build()
    }

    single<Retrofit>(named(NetworkConstants.OAuth.NAME)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.OAuth.BASE_URL)
            .addConverterFactory(get<Converter.Factory>())
            .addCallAdapterFactory(get<CallAdapter.Factory>())
            .callFactory { request ->
                inject<OkHttpClient>(named(OkHttpClientType.OAUTH)).value.newCall(request)
            }
            .build()
    }

    single<OkHttpClient>(named(OkHttpClientType.GITHUB_API)) {
        OkHttpClient.Builder()
            .readTimeout(NetworkConstants.TimeOut.READ, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstants.TimeOut.WRITE, TimeUnit.SECONDS)
            .connectTimeout(NetworkConstants.TimeOut.CONNECTION, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>(named(InterceptorType.LOGGING)))
            .addInterceptor(get<Interceptor>(named(InterceptorType.GITHUB_API)))
            .build()
    }

    single<Retrofit>(named(NetworkConstants.GithubAPI.NAME)) {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.GithubAPI.BASE_URL)
            .addConverterFactory(get<Converter.Factory>())
            .addCallAdapterFactory(get<CallAdapter.Factory>())
            .callFactory { request ->
                inject<OkHttpClient>(named(OkHttpClientType.GITHUB_API)).value.newCall(request)
            }
            .build()
    }
}