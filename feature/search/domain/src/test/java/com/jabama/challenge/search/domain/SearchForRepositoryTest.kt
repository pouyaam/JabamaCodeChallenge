package com.jabama.challenge.search.domain

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.jabama.challenge.search.domain.fakes.FakeSearchRepository
import com.jabama.challenge.search.domain.repo.SearchRepository
import com.jabama.challenge.search.domain.usecase.SearchForRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchForRepositoryTest {

    private lateinit var searchForRepositoryUsecase: SearchForRepository
    private lateinit var searchRepository: SearchRepository

    @Test
    fun searchForRepositoryUsecaseReturnsListOfResults() = runBlocking {
        searchRepository = FakeSearchRepository(requestSuccess = true)
        searchForRepositoryUsecase = SearchForRepository(searchRepository)
        val result = searchForRepositoryUsecase.invoke("any")
        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrNull()).isNotNull()
        assertThat(result.exceptionOrNull()).isNull()
        assertThat(result.getOrNull()?.isNotEmpty()).isNotNull().isTrue()
    }

    @Test
    fun searchForRepositoryUsecaseReturnsNothingWithError() = runBlocking {
        searchRepository = FakeSearchRepository(requestSuccess = false)
        searchForRepositoryUsecase = SearchForRepository(searchRepository)
        val result = searchForRepositoryUsecase.invoke("any")
        assertThat(result.isFailure).isTrue()
        assertThat(result.getOrNull()).isNull()
    }
}