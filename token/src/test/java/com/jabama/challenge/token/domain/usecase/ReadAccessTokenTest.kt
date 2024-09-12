package com.jabama.challenge.token.domain.usecase

import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.domain.data.FakePreferences
import com.jabama.challenge.token.domain.data.FakeTokenRepository
import com.jabama.challenge.token.domain.model.request.RequestAccessToken
import com.jabama.challenge.token.domain.model.response.ResponseAccessToken
import com.jabama.challenge.token.domain.preferences.AbstractPreferences
import com.jabama.challenge.token.domain.repo.TokenRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ReadAccessTokenTest {

    private lateinit var readAccessTokenUseCase: ReadAccessToken
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
        readAccessTokenUseCase = ReadAccessToken(tokenRepository)
    }


    @Test
    fun readTokenWhileTokenSavedSuccessfully() = runBlocking {
        coEvery { accessTokenService.accessToken(any(), any()) } returns ResponseAccessToken(
            accessToken = "ghu_tfRJSq58imjAOKQmh9ZBMW1wBpCiti1K2SZ0",
            tokenType = "bearer"
        )
        tokenRepository.refreshAccessToken(
            mockk<RequestAccessToken>(relaxed = true)
        )

        val token = readAccessTokenUseCase.invoke()
        assertk.assertThat(token).isEqualTo("ghu_tfRJSq58imjAOKQmh9ZBMW1wBpCiti1K2SZ0")
    }

    @Test
    fun nullTokenWhenTokenNotSavedBecauseOfRefreshmentError() = runBlocking {
        coEvery {
            accessTokenService.accessToken(
                any(),
                any()
            )
        } throws (HttpException(
            Response.error<ResponseAccessToken>(
                404,
                ResponseBody.create(null, "")
            )
        ))
        try {
            tokenRepository.refreshAccessToken(
                mockk<RequestAccessToken>(relaxed = true)
            )
        } catch (e: Exception) {
            assertk.assertThat(e).isInstanceOf(HttpException::class)
            assertk.assertThat((e as HttpException).code()).isEqualTo(404)
        }
        val token = readAccessTokenUseCase.invoke()
        assertk.assertThat(token).isEqualTo("null")
    }
}