package com.jabama.challenge.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.search.model.Repo

@Composable
fun RepositoryItemComponent(
    repo: Repo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = repo.name,
            style = MaterialTheme.typography.headlineLarge
        )
        repo.description?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = JabamaTheme.paddings.p8),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "🌟 ${repo.score}",
                style = MaterialTheme.typography.bodyMedium
            )
            repo.language?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
    HorizontalDivider()
}
