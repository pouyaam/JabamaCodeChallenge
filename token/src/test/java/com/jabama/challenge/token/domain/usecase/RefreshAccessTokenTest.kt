package com.jabama.challenge.token.domain.usecase

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.google.gson.JsonParseException
import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.domain.data.FakePreferences
import com.jabama.challenge.token.domain.data.FakeTokenRepository
import com.jabama.challenge.token.domain.model.response.ResponseAccessToken
import com.jabama.challenge.token.domain.preferences.AbstractPreferences
import com.jabama.challenge.token.domain.repo.TokenRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.text.ParseException

class RefreshAccessTokenTest {

    private lateinit var refreshAccessTokenUseCase: RefreshAccessToken
    private lateinit var tokenRepository: TokenRepository
    private lateinit var preferences: AbstractPreferences
    private lateinit var accessTokenService: AccessTokenService

    @Before
    fun setUp() {
        preferences = FakePreferences()

        accessTokenService = mockk()

        tokenRepository = FakeTokenRepository(
            preferences = preferences,
            accessTokenService = accessTokenService
        )
        refreshAccessTokenUseCase = RefreshAccessToken(tokenRepository)
    }

    @Test
    fun requestRefreshTokenSuccessfullyAndSaveInPreferences() = runBlocking {
        coEvery { accessTokenService.accessToken(any(), any()) } returns ResponseAccessToken(
            accessToken = "ghu_tfRJSq58imjAOKQmh9ZBMW1wBpCiti1K2SZ0",
            tokenType = "bearer"
        )

        val code = "code"
        val state = "state"

        refreshAccessTokenUseCase.invoke(code, state)
        assertThat(tokenRepository.readToken()).isEqualTo("ghu_tfRJSq58imjAOKQmh9ZBMW1wBpCiti1K2SZ0")
    }

    @Test
    fun requestRefreshTokenFailedWithNotFoundError() = runBlocking {
        coEvery {
            accessTokenService.accessToken(
                any(),
                any()
            )
        } throws(HttpException(
            Response.error<ResponseAccessToken>(
                404,
                ResponseBody.create(null, "")
            )
        ))
        try {
            refreshAccessTokenUseCase.invoke("5153", "state")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(HttpException::class)
            assertThat((e as HttpException).code()).isEqualTo(404)
        }
        assertThat(tokenRepository.readToken()).isEqualTo("null")
    }

    @Test
    fun requestRefreshTokenFailedWithDeSerializationError() = runBlocking {
        coEvery {
            accessTokenService.accessToken(
                any(),
                any()
            )
        } throws(JsonParseException(""))
        try {
            refreshAccessTokenUseCase.invoke("5153", "state")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(JsonParseException::class)
        }
        assertThat(tokenRepository.readToken()).isEqualTo("null")
    }
}
