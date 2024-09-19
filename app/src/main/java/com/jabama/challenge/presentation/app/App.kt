package com.jabama.challenge.presentation.app

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@App)
//            modules(listOf(appModule, networkModule, accessTokenModule))
        }
    }

    val appModule = module {
//        factory { TokenRepositoryImpl(get()) }
//        single(named(APPLICATION_CONTEXT)) { applicationContext }
//        single { PreferenceManager.getDefaultSharedPreferences(get()) }
    }

}