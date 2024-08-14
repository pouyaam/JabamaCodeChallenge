package com.jabama.challenge.app

import android.app.Application
import com.jabama.challenge.app.di.mainModule
import com.jabama.challenge.core.coroutines.coroutinesModule
import com.jabama.challenge.core.di.coreModule
import com.jabama.challenge.core.network.di.accessTokenModule
import com.jabama.challenge.core.network.di.networkModule
import com.jabama.challenge.repository.di.tokenRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    coreModule,
                    networkModule,
                    coroutinesModule,
                    accessTokenModule,
                    tokenRepositoryModule,
                    mainModule
                )
            )
        }
    }
}