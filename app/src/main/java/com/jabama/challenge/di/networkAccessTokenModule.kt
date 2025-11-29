package com.jabama.challenge.di

import org.koin.dsl.module

val networkAccessTokenModule = module {
    single { getLogger() }
    single { retrofitHttpClient(get()) }
    single { retrofitAccessTokenClient(get()) }
}
