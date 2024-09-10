package com.jabama.challenge.token.di

import com.jabama.challenge.common.di.RETROFIT
import com.jabama.challenge.token.data.repo.AccessTokenDataSourceImpl
import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.data.repo.AccessTokenLocalStorageImpl
import com.jabama.challenge.token.domain.repo.AccessTokenDataSource
import com.jabama.challenge.token.domain.repo.AccessTokenLocalStorage
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }
    factory { AccessTokenDataSourceImpl(get()) } bind AccessTokenDataSource::class

    single { AccessTokenLocalStorageImpl(get()) } bind AccessTokenLocalStorage::class
}