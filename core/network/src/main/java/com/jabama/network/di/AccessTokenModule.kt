package com.jabama.network.di

import com.jabama.network.api.AuthService
import com.jabama.network.datasource.token.AccessTokenDataSource
import com.jabama.network.datasource.token.AccessTokenDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {

    factory<AuthService> {
        get<Retrofit>(named(RETROFIT_AUTH)).create(AuthService::class.java)
    }

    factory<AccessTokenDataSource> {
        AccessTokenDataSourceImpl(get())
    }
}