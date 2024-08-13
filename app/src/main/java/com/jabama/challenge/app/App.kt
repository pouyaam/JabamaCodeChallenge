package com.jabama.challenge.app

import android.app.Application
import com.jabama.challenge.core.coroutines.coroutinesModule
import com.jabama.challenge.network.di.accessTokenModule
import com.jabama.challenge.network.di.networkModule
import com.jabama.challenge.repository.di.tokenRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    accessTokenModule,
                    tokenRepositoryModule,
                    coroutinesModule
                )
            )
        }
    }
}