package com.jabama.designsystem.component.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jabama.core.designsystem.R
import com.jabama.designsystem.component.preview.ThemePreviews
import com.jabama.designsystem.theme.JabamaTheme
import com.jabama.designsystem.theme.Typography

@Composable
fun JabamaErrorView(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetryClicked: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(R.string.oops),
                style = Typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = errorMessage,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 20.dp),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = onRetryClicked) {
                Text(text = stringResource(R.string.retry))
            }
        }
    }
}

@ThemePreviews
@Composable
private fun Preview() {
    JabamaTheme {
        JabamaErrorView(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            errorMessage = "Unfortunately, an error has occurred.",
            onRetryClicked = {}
        )
    }
}