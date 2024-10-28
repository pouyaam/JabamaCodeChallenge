package com.jabama.data.di

import androidx.preference.PreferenceManager
import com.jabama.common.DispatcherQualifier
import com.jabama.common.JabamaDispatchers
import com.jabama.domain.repository.oauth.AccessTokenRepository
import com.jabama.data.repository.oauth.AccessTokenRepositoryImpl
import com.jabama.domain.repository.repositories.RepositoriesSearchRepository
import com.jabama.data.repository.repositories.RepositoriesSearchRepositoryImpl
import com.jabama.domain.repository.token.TokenRepository
import com.jabama.data.repository.token.TokenRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single {
        PreferenceManager.getDefaultSharedPreferences(androidApplication())
    }

    single<AccessTokenRepository> {
        AccessTokenRepositoryImpl(
            get(),
            get(DispatcherQualifier(JabamaDispatchers.DEFAULT))
        )
    }

    single<TokenRepository> {
        TokenRepositoryImpl(
            get()
        )
    }

    single<RepositoriesSearchRepository> {
        RepositoriesSearchRepositoryImpl(
            get(),
            get(DispatcherQualifier(JabamaDispatchers.DEFAULT))
        )
    }

}