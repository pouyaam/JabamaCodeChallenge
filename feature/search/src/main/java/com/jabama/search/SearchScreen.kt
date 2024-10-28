package com.jabama.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jabama.designsystem.component.preview.ThemePreviews
import com.jabama.designsystem.component.view.JabamaErrorView
import com.jabama.designsystem.component.view.JabamaLoadingView
import com.jabama.designsystem.theme.JabamaTheme
import com.jabama.model.Repository
import com.jabama.search.components.NoDataView
import com.jabama.search.components.RepositoryItemView
import com.jabama.search.components.SearchBar
import com.jabama.search.components.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = koinViewModel(),
    navigateToLogin: () -> Unit,
) {

    val searchUiState by viewModel.state.collectAsStateWithLifecycle()

    SearchScreen(
        uiState = searchUiState,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        onLogoutClicked = {
            viewModel.onEvent(SearchEvent.Logout)
        },
        onRetryClicked = {
            viewModel.onEvent(SearchEvent.OnRetryClick)
        },
        onQueryChanged = {
            viewModel.onEvent(SearchEvent.SearchQueryChanged(it))
        },
        onClearQuery = {
            viewModel.onEvent(SearchEvent.OnClearQueryClick)
        }
    )

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SearchEffect.NavigateToLogin -> {
                    navigateToLogin()
                }
            }
        }
    }
}

@Composable
private fun SearchScreen(
    uiState: SearchState,
    modifier: Modifier = Modifier,
    onLogoutClicked: () -> Unit,
    onClearQuery: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onRetryClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(onButtonClick = onLogoutClicked)
        },
        content = { innerPadding ->
            SearchContentView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
                    .then(modifier),
                state = uiState,
                onRetryClicked = onRetryClicked,
                onQueryChanged = onQueryChanged,
                onClearQuery = onClearQuery
            )
        }
    )

}

@Composable
fun SearchContentView(
    modifier: Modifier = Modifier,
    state: SearchState,
    onClearQuery: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onRetryClicked: () -> Unit,
) {
    Column(modifier = modifier) {

        SearchBar(
            query = state.searchQuery.orEmpty(),
            onClearQuery = onClearQuery,
            onQueryChanged = onQueryChanged,
        )

        when {
            state.apiIsLoading -> {
                JabamaLoadingView()
            }

            state.isFailed -> {
                JabamaErrorView(
                    errorMessage = state.errorMessage.orEmpty(),
                    onRetryClicked = onRetryClicked,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                )
            }

            state.searchedRepository?.isEmpty() == false -> {

                LazyColumn {
                    items(
                        items = state.searchedRepository,
                        key = { repository -> "${repository.id}" }
                    ) { repository ->
                        RepositoryItemView(
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .background(MaterialTheme.colorScheme.onBackground)
                                .clip(RoundedCornerShape(2.dp)),
                            name = repository.name,
                            description = repository.description,
                            language = repository.language
                        )
                    }
                }

            }

            else -> {
                NoDataView(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    JabamaTheme {
        SearchScreen(
            uiState = SearchState(
                searchQuery = "hi",
                searchedRepository = listOf(
                    Repository(
                        id = 1,
                        name = "jabama",
                        description = "code challenge",
                        language = "kotlin"
                    )
                )
            ),
            onRetryClicked = {},
            onQueryChanged = {},
            onClearQuery = {},
            onLogoutClicked = {},
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}
