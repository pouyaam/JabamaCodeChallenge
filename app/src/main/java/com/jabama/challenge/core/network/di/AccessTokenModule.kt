package com.jabama.challenge.core.network.di

import com.jabama.challenge.core.network.oauth.AccessTokenService
import com.jabama.challenge.repository.oauth.AccessTokenDataSource
import com.jabama.challenge.repository.oauth.AccessTokenDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val accessTokenModule = module {
    factory<AccessTokenService> { get<Retrofit>().create() }
    factory<AccessTokenDataSource> { AccessTokenDataSourceImpl(get()) }
}