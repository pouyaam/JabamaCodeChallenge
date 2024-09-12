package com.jabama.challenge.auth.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jabama.challenge.auth.presentation.model.WebLoginResponse
import com.jabama.challenge.auth.presentation.viewmodel.AuthenticationViewModel
import com.jabama.challenge.token.data.api.AccessTokenService
import com.jabama.challenge.token.domain.model.response.ResponseAccessToken
import com.jabama.challenge.token.domain.usecase.RefreshAccessToken
import com.jabama.challenge.token.fakes.FakePreferences
import com.jabama.challenge.token.fakes.FakeTokenRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthenticateScreenKtTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var accessTokenService: AccessTokenService
    private lateinit var webLoginResponse: WebLoginResponse

    @Before
    fun setUp() {

        accessTokenService = mockk<AccessTokenService>(relaxed = true)

        webLoginResponse = mockk<WebLoginResponse>(relaxed = true)

        viewModel = AuthenticationViewModel(
            RefreshAccessToken(
                FakeTokenRepository(
                    FakePreferences(),
                    accessTokenService,
                )
            )
        )
    }

    @Test
    fun loginSuccessfully() {
        coEvery {
            accessTokenService.accessToken(any(), any())
        } returns ResponseAccessToken(
            accessToken = "ghu_tfRJSq58imjAOKQmh9ZBMW1wBpCiti1K2SZ0",
            tokenType = "bearer"
        )
        composeRule.setContent {
            AuthenticateScreen(
                webLoginResponse = webLoginResponse,
                viewModel = viewModel,
            )
        }
        // shows as toast
        onView(withText("Login success"))
            .check(matches(isDisplayed()))

    }

    @Test
    fun loginFailed() {
        coEvery {
            accessTokenService.accessToken(any(), any())
        } throws Exception("Unknown error. Please try again.")
        composeRule.setContent {
            AuthenticateScreen(
                webLoginResponse = webLoginResponse,
                viewModel = viewModel,
            )
        }
        // shows as toast
        onView(withText("Login success"))
            .check(matches(isDisplayed()))
    }
}