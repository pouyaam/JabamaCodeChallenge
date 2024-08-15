package com.jabama.challenge.core.token.di

import com.jabama.challenge.core.coroutines.IoDispatcherNamed
import com.jabama.challenge.core.network.NetworkConstants
import com.jabama.challenge.core.token.remote.AccessTokenService
import com.jabama.challenge.core.token.remote.datasource.AccessTokenDataSource
import com.jabama.challenge.core.token.remote.datasource.AccessTokenDataSourceImpl
import com.jabama.challenge.core.token.repository.TokenRepository
import com.jabama.challenge.core.token.repository.TokenRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val tokenModule = module {
    factory <AccessTokenService> { inject<Retrofit>(named(NetworkConstants.OAuth.NAME)).value.create() }
    factory<AccessTokenDataSource> { AccessTokenDataSourceImpl(get()) }

    single<TokenRepository> {
        TokenRepositoryImpl(
            preferencesDataSource = get(),
            accessTokenDataSource = get(),
            ioDispatcher = get(IoDispatcherNamed)
        )
    }
}