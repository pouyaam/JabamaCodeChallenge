package com.jabama.challenge.di

import androidx.preference.PreferenceManager
import com.jabama.challenge.data.api.AccessTokenApiService
import com.jabama.challenge.data.api.RepositoryApiService
import org.koin.dsl.module
import retrofit2.Retrofit


val appModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(get()) }
    single<AccessTokenApiService> { get<Retrofit>().create(AccessTokenApiService::class.java) }
    single<RepositoryApiService> { get<Retrofit>().create(RepositoryApiService::class.java) }
}
