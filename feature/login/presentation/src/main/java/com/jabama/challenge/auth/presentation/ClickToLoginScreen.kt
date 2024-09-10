package com.jabama.challenge.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jabama.challenge.common.R

@Composable
fun ClickToLoginScreen(modifier: Modifier = Modifier, onClick: () -> Unit) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onClick
        ) {
            Text(text = stringResource(R.string.click_to_login))
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewClickToLoginScreen() {
    MaterialTheme {
        ClickToLoginScreen(onClick = {})
    }
}

