package com.jabama.challenge.navigation

import androidx.navigation.navDeepLink

val navigationDeepLinks = listOf(navDeepLink {
    uriPattern =
        "jabamacode://loginresult?code={${NavigationRoutes.AuthenticateRoute().code}}&state={${NavigationRoutes.AuthenticateRoute().state}}"
})