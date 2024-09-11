package com.jabama.challenge.search.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jabama.challenge.search.presentation.viewmodel.SearchEffects
import com.jabama.challenge.search.presentation.viewmodel.SearchEvents
import com.jabama.challenge.search.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel,
) {

    LaunchedEffect(key1 = viewModel.effects) {
        viewModel.effects.collect { effect ->
            //TODO handle effects
            when (effect) {
                is SearchEffects.ResultSuccess -> {
                }

                is SearchEffects.ResultFailure -> {
                }

                is SearchEffects.AuthFailed -> {
                }
            }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = modifier.fillMaxSize()) {
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
        if (state.isSearching) {
            SearchInProgressPlaceHolder(modifier)
        } else if (state.searchResult.isEmpty()) {
            SearchIsEmptyPlaceHolder(modifier)
        } else if (state.searchResult.isNotEmpty()) {
            SearchResultComposable(modifier, state.searchResult)
        }
    }
}