package com.jabama.challenge.app

import android.app.Application
import com.jabama.challenge.app.di.mainModule
import com.jabama.challenge.core.coroutines.coroutinesModule
import com.jabama.challenge.core.prefs.di.preferencesModule
import com.jabama.challenge.core.network.di.networkModule
import com.jabama.challenge.core.token.di.tokenModule
import com.jabama.challenge.login.di.loginModule
import com.jabama.challenge.search.di.searchModule
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
                    preferencesModule,
                    networkModule,
                    coroutinesModule,
                    tokenModule,
                    mainModule,
                    loginModule,
                    searchModule
                )
            )
        }
    }
}