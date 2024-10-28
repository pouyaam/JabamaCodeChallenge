package com.jabama.challenge.app

import android.app.Application
import com.jabama.challenge.authentication.di.authenticationModule
import com.jabama.common.di.dispatchersModule
import com.jabama.data.di.dataModule
import com.jabama.login.di.loginModule
import com.jabama.network.di.accessTokenModule
import com.jabama.network.di.networkModule
import com.jabama.network.di.repositoriesModule
import com.jabama.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class JabamaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@JabamaApplication)

            modules(
                listOf(
                    networkModule,
                    accessTokenModule,
                    dispatchersModule,
                    dataModule,
                    authenticationModule,
                    loginModule,
                    repositoriesModule,
                    searchModule
                )
            )
        }
    }
}