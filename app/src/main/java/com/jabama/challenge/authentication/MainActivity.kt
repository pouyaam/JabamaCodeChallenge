package com.jabama.challenge.authentication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jabama.challenge.authentication.navigation.JabamaAppNavigation
import com.jabama.common.AuthCoordinator
import com.jabama.designsystem.component.view.JabamaLoadingView
import com.jabama.designsystem.theme.JabamaTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthenticationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            enableEdgeToEdge()

            val state by authViewModel.state.collectAsStateWithLifecycle()

            JabamaTheme {
                if (state.chooseDestinationLoading) {
                    JabamaLoadingView()
                } else {
                    JabamaAppNavigation(
                        isAuthenticated = state.isAuthenticated,
                        onNavigateToAuthUrl = ::launchAuthorizationUrl
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        authViewModel.onEvent(AuthenticationEvent.CheckAuthStatus)
    }

    private fun launchAuthorizationUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun handleRedirectUri(uri: Uri?) {
        uri?.getQueryParameter(CODE_KEY)?.let { code ->
            lifecycleScope.launch {
                AuthCoordinator.emitAuthCode(code)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleRedirectUri(intent.data)
    }

    companion object {
        private const val CODE_KEY = "code"
    }
}


