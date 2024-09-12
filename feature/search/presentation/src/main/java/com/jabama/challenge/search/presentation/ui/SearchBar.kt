package com.jabama.challenge.search.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jabama.challenge.search.presentation.R


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    showHint: Boolean = true,
    onQueryChange: (String) -> Unit = {},
    onSearch: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {}
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = {
            AnimatedVisibility(showHint) {
                Text(
                    text = stringResource(R.string.search_hint),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .semantics { contentDescription = "Click here to start type query" }
            .onFocusChanged(onFocusChanged),
        trailingIcon = {
            IconButton(onClick = onSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon_content_description),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
    )
}

@Preview
@Composable
private fun PreviewSearchBar() {
    SearchBar(query = "")
}