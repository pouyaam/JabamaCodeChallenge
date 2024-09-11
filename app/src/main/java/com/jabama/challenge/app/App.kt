package com.jabama.challenge.app

import android.app.Application
import com.jabama.challenge.auth.presentation.di.loginPresentationModules
import com.jabama.challenge.common.di.networkModule
import com.jabama.challenge.di.appModule
import com.jabama.challenge.search.presentation.di.searchModule
import com.jabama.challenge.token.di.accessTokenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, networkModule, accessTokenModule, loginPresentationModules, searchModule))
        }
    }

}