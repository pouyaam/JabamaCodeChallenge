package com.jabama.challenge.login.di

import com.jabama.challenge.login.LoginViewModel
import com.jabama.challenge.login.model.LoginError
import com.jabama.challenge.login.model.LoginUiState
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
