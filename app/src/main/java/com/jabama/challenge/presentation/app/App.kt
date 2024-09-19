package com.jabama.challenge.presentation.app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.jabama.challenge.data.auth.AuthRepository
import com.jabama.challenge.data.auth.AuthRepositoryImpl
import com.jabama.challenge.domain.auth.SignInWithGithubUseCase
import com.jabama.challenge.domain.auth.SignInWithGithubUseCaseImpl
import com.jabama.challenge.presentation.auth.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@App) // Provide the application context
            modules(appModule)
        }
    }

    private val appModule = module {
        single { FirebaseAuth.getInstance() }
        single<AuthRepository> { AuthRepositoryImpl(get()) }
        single<SignInWithGithubUseCase> { SignInWithGithubUseCaseImpl(get()) }
        viewModel { AuthViewModel(get()) }
    }
}