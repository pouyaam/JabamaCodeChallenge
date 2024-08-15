package com.jabama.challenge.github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.core.ui.components.LoadingComponent
import com.jabama.challenge.core.ui.components.RetryComponent
import com.jabama.challenge.feature.search.SearchActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginUriActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            JabamaTheme {
                val shouldOpenAuthorizePage by remember {
                    derivedStateOf {
                        uiState.shouldOpenAuthorizePage
                    }
                }

                LaunchedEffect(key1 = shouldOpenAuthorizePage) {
                    if (shouldOpenAuthorizePage) {
                        openGithubAuthorizePage()
                    }
                }

                LaunchedEffect(key1 = viewModel.searchPageEvent) {
                    viewModel.searchPageEvent.onEach {
                        navigateToSearchPage()
                    }.launchIn(lifecycleScope)
                }

                Scaffold { innerPadding ->

                    when {
                        uiState.isLoading ->
                            LoadingComponent(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                            )

                        else ->
                            RetryComponent(
                                text = stringResource(id = uiState.error.message),
                                textButton = stringResource(id = uiState.error.textButton),
                                onClick = viewModel::onErrorButtonClick,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxSize()
                            )
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent.action != Intent.ACTION_VIEW) return
        viewModel.onReceivedCode(intent.data?.getQueryParameter(CODE_KEY))
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    private fun openGithubAuthorizePage() {
        Intent(Intent.ACTION_VIEW, Uri.parse(AUTHORIZE_URL)).also {
            startActivity(it)
        }
    }

    private fun navigateToSearchPage() {
        Intent(this, SearchActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    companion object {
        private const val AUTHORIZE_URL = "https://github.com/login/oauth/authorize?client_id=${BuildConfig.CLIENT_ID}&redirect_uri=${BuildConfig.REDIRECT_URI}&scope=repo user&state=0"
        private const val CODE_KEY = "code"
    }
}