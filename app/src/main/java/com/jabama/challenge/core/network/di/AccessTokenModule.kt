package com.jabama.challenge.core.network.di

import com.jabama.challenge.repository.oauth.AccessTokenDataSource
import com.jabama.challenge.repository.oauth.AccessTokenDataSourceImpl
import org.koin.dsl.module

val accessTokenModule = module {
    factory<AccessTokenDataSource> { AccessTokenDataSourceImpl(get()) }
}