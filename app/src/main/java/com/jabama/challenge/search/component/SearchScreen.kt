package com.jabama.challenge.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.core.ui.components.RetryComponent
import com.jabama.challenge.github.R
import com.jabama.challenge.search.model.ErrorType
import com.jabama.challenge.search.model.PageState
import com.jabama.challenge.search.model.SearchUiState
import kotlinx.collections.immutable.persistentListOf

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onQueryChange: (String) -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = text,
                onQueryChange = {
                    text = it
                    onQueryChange(it)
                },
                onSearch = onQueryChange,
                active = false,
                onActiveChange = {},
                placeholder = { Text("Search") },
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Search,
                        contentDescription = "search",
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(JabamaTheme.paddings.p16),
                content = {}
            )
        }
    ) { innerPadding ->

        when (uiState.state) {
            is PageState.Error ->
                RetryComponent(
                    text = stringResource(id = uiState.state.type.textId),
                    onClick = onRetryClick,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                )

            PageState.Loading ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            PageState.NoItems ->

                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.no_item))
                }

            else ->
                SearchContent(
                    uiState = uiState,
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = JabamaTheme.paddings.p16)
                        .fillMaxSize()
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun NoItemsPreview() {
    JabamaTheme {
        val uiState = SearchUiState(
            state = PageState.NoItems,
            items = persistentListOf(),
            page = 0,
            totalCount = 0,
            isLastPage = false
        )
        SearchScreen(
            uiState = uiState,
            onQueryChange = {},
            onRetryClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun LoadingPreview() {
    JabamaTheme {
        val uiState = SearchUiState(
            state = PageState.Loading,
            items = persistentListOf(),
            page = 0,
            totalCount = 0,
            isLastPage = false
        )
        SearchScreen(
            uiState = uiState,
            onQueryChange = {},
            onRetryClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun ErrorPreview() {
    JabamaTheme {
        val uiState = SearchUiState(
            state = PageState.Error(ErrorType.SPAMMED),
            items = persistentListOf(),
            page = 0,
            totalCount = 0,
            isLastPage = false
        )
        SearchScreen(
            uiState = uiState,
            onQueryChange = {},
            onRetryClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}