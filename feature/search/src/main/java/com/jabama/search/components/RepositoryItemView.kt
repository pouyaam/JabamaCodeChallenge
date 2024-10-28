package com.jabama.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jabama.designsystem.component.preview.ThemePreviews
import com.jabama.designsystem.theme.JabamaTheme
import com.jabama.feature.search.R

@Composable
internal fun RepositoryItemView(
    modifier: Modifier = Modifier,
    name: String,
    description: String?,
    language: String?,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f).padding(end = 4.dp)
            )

            if (!language.isNullOrEmpty()) {
                Text(
                    text = stringResource(R.string.language, language),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }

        if (!description.isNullOrEmpty()) {
            Text(
                text = description,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    JabamaTheme {
        RepositoryItemView(
            name = "Tetris",
            description = "A C implementation of Tetris using Pennsim through LC4",
            language = "kotlin",
            modifier = Modifier.background(MaterialTheme.colorScheme.onBackground)
        )
    }
}