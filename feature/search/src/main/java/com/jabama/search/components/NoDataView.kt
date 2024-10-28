package com.jabama.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jabama.designsystem.component.preview.ThemePreviews
import com.jabama.designsystem.theme.JabamaTheme
import com.jabama.feature.search.R

@Composable
fun NoDataView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.noData),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    JabamaTheme {
        NoDataView(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}