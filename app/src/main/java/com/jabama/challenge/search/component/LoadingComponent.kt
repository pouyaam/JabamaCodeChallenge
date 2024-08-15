package com.jabama.challenge.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jabama.challenge.core.designsystem.theme.JabamaTheme

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.padding(JabamaTheme.paddings.p16)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    JabamaTheme {
        LoadingComponent(
            modifier = Modifier.fillMaxWidth()
        )
    }
}