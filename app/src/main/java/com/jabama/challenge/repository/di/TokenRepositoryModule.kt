package com.jabama.challenge.repository.di

import com.jabama.challenge.repository.token.TokenRepository
import com.jabama.challenge.repository.token.TokenRepositoryImpl
import org.koin.dsl.module

val tokenRepositoryModule = module {
    factory<TokenRepository> { TokenRepositoryImpl(get()) }
}