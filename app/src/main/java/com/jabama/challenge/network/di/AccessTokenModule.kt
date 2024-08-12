package com.jabama.challenge.network.di

import com.jabama.challenge.network.oauth.AccessTokenService
import com.jabama.challenge.repository.oauth.AccessTokenDataSource
import com.jabama.challenge.repository.oauth.AccessTokenDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }
    factory { AccessTokenDataSourceImpl(get()) as AccessTokenDataSource }
}