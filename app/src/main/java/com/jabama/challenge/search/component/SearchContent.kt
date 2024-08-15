package com.jabama.challenge.search.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.search.model.PageState
import com.jabama.challenge.search.model.SearchUiState

@Composable
fun SearchContent(
    uiState: SearchUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            count = uiState.items.size,
            key = { index -> uiState.items[index].id }
        ) { index ->
            val item = uiState.items[index]
            RepositoryItemComponent(
                repo = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(JabamaTheme.paddings.p16)
            )
        }
    }
}