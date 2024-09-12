package com.jabama.challenge.search.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.jabama.challenge.search.domain.fakes.FakeLanguageColorRepository
import com.jabama.challenge.search.domain.fakes.FakeSearchRepository
import com.jabama.challenge.search.domain.usecase.GetLanguageColorFromName
import com.jabama.challenge.search.domain.usecase.SearchForRepository
import com.jabama.challenge.search.presentation.ui.SearchScreen
import com.jabama.challenge.search.presentation.viewmodel.SearchViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var viewmodel: SearchViewModel

    @Before
    fun setup() {
        viewmodel = SearchViewModel(
            SearchForRepository(FakeSearchRepository(true)),
            GetLanguageColorFromName(FakeLanguageColorRepository())
        )
    }

    @Test
    fun testSearchScreen() {
        composeRule.setContent {
            var authFailedState by remember { mutableStateOf(false) }
            SearchScreen(viewModel = viewmodel, onAuthFailed = { authFailedState = true })
        }
        composeRule.onNodeWithContentDescription("Click here to start type query")
            .assertExists()
            .assertIsDisplayed()
            .performClick()
            .performTextInput("hello")
        composeRule.onAllNodesWithText("my repository").onFirst().assertExists()
    }
}