package com.jabama.network.di

import com.jabama.network.api.SearchService
import com.jabama.network.datasource.repository.RepositoryDataSource
import com.jabama.network.datasource.repository.RepositoryDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoriesModule = module {

    factory<SearchService> {
        get<Retrofit>(named(RETROFIT_SEARCH)).create(SearchService::class.java)
    }

    factory<RepositoryDataSource> {
        RepositoryDataSourceImpl(get())
    }

}