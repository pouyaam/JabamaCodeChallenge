package com.jabama.login.di

import com.jabama.domain.usecase.aouth.GetAccessTokenUseCase
import com.jabama.domain.usecase.token.SaveTokenUseCase
import com.jabama.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {

    factory {
        GetAccessTokenUseCase(repository = get())
    }

    factory {
        SaveTokenUseCase(repository = get())
    }

    viewModel {
        LoginViewModel(
            accessTokenUseCase = get(),
            saveTokenUseCase = get()
        )
    }
}