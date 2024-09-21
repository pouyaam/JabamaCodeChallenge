package com.jabama.challenge.presentation.app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.jabama.challenge.data.auth.AuthRepository
import com.jabama.challenge.data.auth.AuthRepositoryImpl
import com.jabama.challenge.data.search.GitHubApiService
import com.jabama.challenge.data.search.SearchRepository
import com.jabama.challenge.data.search.SearchRepositoryImpl
import com.jabama.challenge.domain.auth.SignInWithGithubUseCase
import com.jabama.challenge.domain.auth.SignInWithGithubUseCaseImpl
import com.jabama.challenge.domain.search.SearchRepositoriesUseCase
import com.jabama.challenge.domain.search.SearchRepositoriesUseCaseImpl
import com.jabama.challenge.presentation.auth.AuthViewModel
import com.jabama.challenge.presentation.search.SearchViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

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

        val json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        }

        single {
            Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(
                    json.asConverterFactory(
                        "application/json; charset=UTF8".toMediaType()
                    )
                )
                .build()
                .create(GitHubApiService::class.java)
        }

        single<SearchRepository> { SearchRepositoryImpl(get()) }
        single<SearchRepositoriesUseCase> { SearchRepositoriesUseCaseImpl(get()) }
        viewModel { SearchViewModel(get()) }

    }
}