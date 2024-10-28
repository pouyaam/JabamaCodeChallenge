package com.jabama.search.di

import com.jabama.domain.usecase.repositories.GetRepositoriesUseCase
import com.jabama.domain.usecase.token.ClearTokenUseCase
import com.jabama.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {

    factory {
        GetRepositoriesUseCase(repository = get())
    }

    factory {
        ClearTokenUseCase(repository = get())
    }

    viewModel {
        SearchViewModel(
            getRepositoriesUseCase = get(),
            clearTokenUseCase = get()
        )
    }

}