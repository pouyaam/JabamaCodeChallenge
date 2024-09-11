package com.jabama.challenge.search.presentation.di

import com.jabama.challenge.common.di.RETROFIT
import com.jabama.challenge.search.data.remote.SearchRepoApi
import com.jabama.challenge.search.data.repo.LanguageColorRepositoryImpl
import com.jabama.challenge.search.data.repo.SearchRepositoryImpl
import com.jabama.challenge.search.domain.repo.LanguageColorRepository
import com.jabama.challenge.search.domain.repo.SearchRepository
import com.jabama.challenge.search.domain.usecase.GetLanguageColorFromName
import com.jabama.challenge.search.domain.usecase.SearchForRepository
import com.jabama.challenge.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val searchModule = module {
    factory { get<Retrofit>(named(RETROFIT)).create(SearchRepoApi::class.java) }
    factory { SearchRepositoryImpl(get()) } bind SearchRepository::class
    factory { SearchForRepository(get()) }

    factory { LanguageColorRepositoryImpl(get()) } bind LanguageColorRepository::class
    factory { GetLanguageColorFromName(get()) }

    viewModel { SearchViewModel(get(), get()) }
}