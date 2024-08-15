package com.jabama.challenge.search.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jabama.challenge.core.designsystem.theme.JabamaTheme

@Composable
fun PaginationErrorComponent(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onRetryClick
    ) {
        Icon(
            modifier = Modifier
                .size(JabamaTheme.sizes.s48),
            imageVector = Icons.Filled.Refresh,
            contentDescription = "pagination error",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}