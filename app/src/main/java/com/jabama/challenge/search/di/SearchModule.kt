package com.jabama.challenge.search.di

import com.jabama.challenge.core.coroutines.IoDispatcherNamed
import com.jabama.challenge.core.network.NetworkConstants
import com.jabama.challenge.search.SearchViewModel
import com.jabama.challenge.search.model.PageState
import com.jabama.challenge.search.model.SearchUiState
import com.jabama.challenge.search.remote.SearchApiService
import com.jabama.challenge.search.repository.SearchRepository
import com.jabama.challenge.search.repository.SearchRepositoryImpl
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val searchModule = module {
    factory<SearchApiService> {
        inject<Retrofit>(named(NetworkConstants.GithubAPI.NAME)).value.create()
    }

    single<SearchRepository> {
        SearchRepositoryImpl(
            api = get(),
            ioDispatcher = get(IoDispatcherNamed)
        )
    }

    viewModel {
        SearchViewModel(
            initialUiState = SearchUiState(
                state = PageState.NoItems,
                items = persistentListOf(),
                page = 0,
                totalCount = 0,
                isLastPage = false
            ),
            repository = get()
        )
    }
}
