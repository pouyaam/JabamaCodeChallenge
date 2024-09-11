package com.jabama.challenge.search.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jabama.challenge.search.presentation.viewmodel.SearchEffects
import com.jabama.challenge.search.presentation.viewmodel.SearchEvents
import com.jabama.challenge.search.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
    onAuthFailed: () -> Unit = {},
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.effects) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is SearchEffects.ResultSuccess -> {}
                is SearchEffects.ResultFailure -> snackBarHostState.showSnackbar(
                    message = effect.message.asString(
                        context
                    )
                )

                is SearchEffects.AuthFailed -> onAuthFailed()
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(modifier = modifier, topBar = {
        SearchBar(
            query = state.query,
            showHint = state.isHintVisible,
            onQueryChange = {
                viewModel.invokeEvent(SearchEvents.OnQueryChange(it))
            },
            onSearch = {
                viewModel.invokeEvent(SearchEvents.OnSearch)
            },
            onFocusChanged = {
                viewModel.invokeEvent(SearchEvents.OnSearchFocusChange(it.isFocused))
            },
        )
    }, snackbarHost = {
        SnackbarHost(hostState = snackBarHostState)
    }, content = { paddingValues ->
        val paddingModifier = Modifier.padding(paddingValues)
        if (state.isSearching) {
            SearchInProgressPlaceHolder(paddingModifier)
        } else if (state.searchResult.isEmpty()) {
            SearchIsEmptyPlaceHolder(paddingModifier)
        } else if (state.searchResult.isNotEmpty()) {
            SearchResultComposable(paddingModifier, state.searchResult)
        }
    })
}