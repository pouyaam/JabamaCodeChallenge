@file:OptIn(ExperimentalMaterial3Api::class)

package com.jabama.challenge.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.login.LoginUriActivity
import com.jabama.challenge.search.component.SearchScreen
import com.jabama.challenge.search.model.SearchEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JabamaTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = viewModel.event) {
                    viewModel.event.onEach {
                        when (it) {
                            SearchEvent.NavigateToLoginPage -> goToLoginPage()
                        }
                    }.launchIn(lifecycleScope)
                }

                Surface {
                    SearchScreen(
                        uiState = uiState,
                        onQueryChange = viewModel::onQueryChange,
                        onRetryClick = viewModel::retry,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    private fun goToLoginPage() {
        Intent(this, LoginUriActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }
}