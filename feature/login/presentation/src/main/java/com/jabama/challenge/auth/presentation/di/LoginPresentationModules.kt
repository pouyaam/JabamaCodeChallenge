package com.jabama.challenge.auth.presentation.di

import com.jabama.challenge.auth.presentation.viewmodel.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginPresentationModules = module {
    viewModel { AuthenticationViewModel(get()) }
}