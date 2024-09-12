package com.jabama.challenge.search.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jabama.challenge.search.domain.fakes.FakeLanguageColorRepository
import com.jabama.challenge.search.domain.repo.LanguageColorRepository
import com.jabama.challenge.search.domain.usecase.GetLanguageColorFromName
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetLanguageColorFromNameTest {

    lateinit var getLanguageColorFromNameUseCase: GetLanguageColorFromName
    lateinit var repository: LanguageColorRepository

    @Before
    fun setup() {
        repository = FakeLanguageColorRepository()
        getLanguageColorFromNameUseCase = GetLanguageColorFromName(repository)
    }

    @Test
    fun getColorIfItIsExistResultesItsColorCodeAsInteger() = runBlocking {
        val lanColor = getLanguageColorFromNameUseCase("kotlin")
        assertThat(lanColor).isEqualTo(0x00A97BFF)
    }

    @Test
    fun getColorIfIsNotExistResultsFailure() = runTest {
        assertFailure { getLanguageColorFromNameUseCase("html") }
    }

}