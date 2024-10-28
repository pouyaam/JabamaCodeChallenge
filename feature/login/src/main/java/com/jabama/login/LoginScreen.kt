package com.jabama.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jabama.designsystem.component.preview.ThemePreviews
import com.jabama.designsystem.theme.JabamaTheme
import com.jabama.designsystem.theme.Typography
import com.jabama.feature.login.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
    navigateToSearch: () -> Unit,
    onNavigateToAuthUrl: (String) -> Unit
) {
    val loginUiState by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = loginUiState,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        onButtonClick = {
            viewModel.onEvent(LoginEvent.AuthorizeClicked)
        }
    )

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.NavigateToSearch -> {
                    navigateToSearch()
                }

                is LoginEffect.NavigateToAuthUrl -> {
                    onNavigateToAuthUrl(effect.url)
                }
            }
        }
    }
}


@Composable
internal fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginState,
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val (infoTextResId, infoColor) = if (uiState.isFailed)
            R.string.error_message to MaterialTheme.colorScheme.error
        else
            R.string.press_authorize to MaterialTheme.colorScheme.primary

        Text(
            text = stringResource(infoTextResId),
            style = Typography.bodySmall,
            color = infoColor
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = onButtonClick,
        ) {
            if (uiState.authorizeButtonIsLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = stringResource(R.string.authorize))
            }
        }
    }

}

@ThemePreviews
@Composable
private fun Preview() {
    JabamaTheme {
        Surface {
            LoginScreen(
                uiState = LoginState(isFailed = true),
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                onButtonClick = {}
            )
        }
    }
}
