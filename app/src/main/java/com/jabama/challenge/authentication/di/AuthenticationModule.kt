package com.jabama.challenge.authentication.di

import com.jabama.challenge.authentication.AuthenticationViewModel
import com.jabama.domain.usecase.token.ReadTokenUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {

    factory {
        ReadTokenUseCase(
            get()
        )
    }

    viewModel {
        AuthenticationViewModel(get())
    }

}