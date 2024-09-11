package com.jabama.challenge.github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jabama.challenge.auth.presentation.AuthenticateScreen
import com.jabama.challenge.auth.presentation.ClickToLoginScreen
import com.jabama.challenge.auth.presentation.model.WebLoginResponse
import com.jabama.challenge.auth.presentation.viewmodel.AuthenticationViewModel
import com.jabama.challenge.common.constants.GITHUB_WEB_FLOW_URL
import com.jabama.challenge.navigation.NavigationRoutes
import com.jabama.challenge.navigation.authenticationArguments
import com.jabama.challenge.navigation.navigationDeepLinks
import com.jabama.challenge.search.presentation.ui.SearchScreen
import com.jabama.challenge.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // TODO Material theme will be applied
                MainAppNavigationScreen(modifier = Modifier)
        }
    }

    @Composable
    fun MainAppNavigationScreen(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.SearchRoute.route
        ) {
            composable(route = NavigationRoutes.LoginRoute.route) {
                ClickToLoginScreen(
                    modifier = modifier,
                    onClick = {
                        val url = GITHUB_WEB_FLOW_URL
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(url)
                        startActivity(i)
                    }
                )
            }
            composable(
                route = NavigationRoutes.AuthenticateRoute().route,
                deepLinks = navigationDeepLinks,
                arguments = authenticationArguments
            ) { entry ->
                val authenticationViewModel by viewModel<AuthenticationViewModel>()
                val code =
                    entry.arguments?.getString(NavigationRoutes.AuthenticateRoute().code) ?: ""
                val state =
                    entry.arguments?.getString(NavigationRoutes.AuthenticateRoute().state) ?: ""
                AuthenticateScreen(
                    modifier = modifier,
                    webLoginResponse = WebLoginResponse(code, state),
                    viewModel = authenticationViewModel
                )
            }
            composable(
                route = NavigationRoutes.SearchRoute.route,
            ) {
                val searchViewModel by viewModel<SearchViewModel>()
                SearchScreen(
                    modifier = modifier,
                    viewModel = searchViewModel,
                    onAuthFailed = {
                        navController.navigate(NavigationRoutes.LoginRoute.route) {
                            popUpTo(NavigationRoutes.SearchRoute.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }

    @Preview
    @Composable
    private fun PreviewMainAppScreen() {
        MaterialTheme {
            MainAppNavigationScreen(modifier = Modifier)
        }
    }
}
