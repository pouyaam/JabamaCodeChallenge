package com.jabama.challenge.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.github.R

@Composable
fun RetryComponent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.general_error),
    textButton: String = stringResource(id = R.string.retry),
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = JabamaTheme.paddings.p16)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(JabamaTheme.sizes.s32))

        Button(onClick = onClick) {
            Text(text = textButton)
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