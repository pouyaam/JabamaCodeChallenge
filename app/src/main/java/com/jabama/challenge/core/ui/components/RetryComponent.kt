package com.jabama.challenge.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.github.R

@Composable
fun RetryComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.global_error))

        Spacer(modifier = Modifier.size(JabamaTheme.sizes.s32))

        Button(onClick = onClick) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    JabamaTheme {
        RetryComponent(
            modifier = Modifier.fillMaxSize(),
            onClick = {}
        )
    }
}