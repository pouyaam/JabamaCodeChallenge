package com.jabama.challenge.token.di

import com.jabama.challenge.common.di.ACCESS_TOKEN
import com.jabama.challenge.common.di.RETROFIT
import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.data.preferences.ActualSharedPreferences
import com.jabama.challenge.token.data.repo.TokenRepositoryImpl
import com.jabama.challenge.token.domain.preferences.AbstractPreferences
import com.jabama.challenge.token.domain.repo.TokenRepository
import com.jabama.challenge.token.domain.usecase.ReadAccessToken
import com.jabama.challenge.token.domain.usecase.RefreshAccessToken
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {
    factory { ActualSharedPreferences(get()) } bind AbstractPreferences::class
    factory { get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java) }
    factory { TokenRepositoryImpl(get(), get()) } bind TokenRepository::class

    factory { RefreshAccessToken(get()) }
    factory(named(ACCESS_TOKEN)) { ReadAccessToken(get()).invoke() }

}