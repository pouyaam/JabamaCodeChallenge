package com.jabama.challenge.github.di

import com.jabama.challenge.github.LoginViewModel
import com.jabama.challenge.github.model.LoginError
import com.jabama.challenge.github.model.LoginUiState
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    factory {
        LoginUiState(
            isLoading = false,
            authorizeCode = null,
            error = LoginError.NO_CODE
        )
    }

    viewModel {
        LoginViewModel(
            initialUiState = get(),
            tokenRepository = get()
        )
    }
}
