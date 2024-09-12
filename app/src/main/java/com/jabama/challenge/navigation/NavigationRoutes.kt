package com.jabama.challenge.navigation

sealed class NavigationRoutes(val route: String) {
    data object LoginRoute : NavigationRoutes("login")
    data class AuthenticateRoute(val code: String = "code", val state: String = "state") :
        NavigationRoutes("authenticate")
    data object SearchRoute : NavigationRoutes("search")
}