package com.jabama.challenge.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

val authenticationArguments = listOf(
    navArgument(name = NavigationRoutes.AuthenticateRoute().code) {
        type = NavType.StringType
        defaultValue = ""
    },
    navArgument(name = NavigationRoutes.AuthenticateRoute().state) {
        type = NavType.StringType
        defaultValue = ""
    },
)