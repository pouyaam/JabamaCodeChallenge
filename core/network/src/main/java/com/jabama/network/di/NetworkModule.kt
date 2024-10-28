package com.jabama.network.di

import com.google.gson.GsonBuilder
import com.jabama.common.Resource
import com.jabama.common.ResourceDeserializer
import com.jabama.network.interceptor.AuthInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val OK_HTTP = "OK_HTTP"
const val RETROFIT_SEARCH = "RETROFIT_SEARCH"
const val RETROFIT_AUTH = "RETROFIT_AUTH"
private const val READ_TIMEOUT = "READ_TIMEOUT"
private const val WRITE_TIMEOUT = "WRITE_TIMEOUT"
private const val CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT"

private val gson = GsonBuilder()
    .registerTypeAdapter(Resource::class.java, ResourceDeserializer())
    .create()

val networkModule = module {

    single<Long>(named(READ_TIMEOUT)) { 30 * 1000 }
    single<Long>(named(WRITE_TIMEOUT)) { 10 * 1000 }
    single<Long>(named(CONNECTION_TIMEOUT)) { 10 * 1000 }

    single<Interceptor> {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single(named(OK_HTTP)) {
        OkHttpClient.Builder()
            .readTimeout(get(named(READ_TIMEOUT)), TimeUnit.MILLISECONDS)
            .writeTimeout(get(named(WRITE_TIMEOUT)), TimeUnit.MILLISECONDS)
            .connectTimeout(get(named(CONNECTION_TIMEOUT)), TimeUnit.MILLISECONDS)
            .addInterceptor(get<Interceptor>())
            .addInterceptor(AuthInterceptor())
            .build()
    }

    single(named(RETROFIT_SEARCH)) {
        Retrofit.Builder()
            .client(get(named(OK_HTTP)))
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    single(named(RETROFIT_AUTH)) {
        Retrofit.Builder()
            .client(get(named(OK_HTTP)))
            .baseUrl("https://github.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}