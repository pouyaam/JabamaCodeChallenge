package com.jabama.challenge.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jabama.challenge.core.ui.components.LoadingComponent
import com.jabama.challenge.core.ui.components.RetryComponent
import com.jabama.challenge.app.model.MainEvent
import com.jabama.challenge.core.designsystem.theme.JabamaTheme
import com.jabama.challenge.feature.search.SearchActivity
import com.jabama.challenge.github.LoginUriActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JabamaTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {
                    viewModel.event.onEach {
                        when (it) {
                            MainEvent.NavigateToLoginPage -> navigateToLoginPage()
                            MainEvent.NavigateToSearchPage -> navigateToSearchPage()
                        }
                        finish()
                    }.launchIn(lifecycleScope)
                }

                Scaffold { innerPadding ->
                    if (uiState.isLoading) {
                        LoadingComponent(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }

                    if (uiState.isError) {
                        RetryComponent(
                            onClick = viewModel::retry,
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        )
                    }
                }
            }
        }
    }

    private fun navigateToLoginPage() {
        val intent = Intent(this, LoginUriActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSearchPage() {
        Intent(this, SearchActivity::class.java).also {
            startActivity(it)
        }
    }
}
