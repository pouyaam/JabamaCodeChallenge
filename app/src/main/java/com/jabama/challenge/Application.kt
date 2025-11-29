package com.jabama.challenge

import android.app.Application
import com.jabama.challenge.di.githubModule
import com.jabama.challenge.di.appModule
import com.jabama.challenge.di.networkRepositoryModule
import com.jabama.challenge.di.networkAccessTokenModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(appModule, networkAccessTokenModule, githubModule,networkRepositoryModule))
        }
    }
}