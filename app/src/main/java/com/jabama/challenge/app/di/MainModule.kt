package com.jabama.challenge.app.di

import com.jabama.challenge.app.MainViewModel
import com.jabama.challenge.app.model.MainUiState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        MainUiState(
            isLoading = true,
            isError = false
        )
    }

    viewModel {
        MainViewModel(
            initialUiState = get(),
            tokenRepository = get()
        )
    }
}