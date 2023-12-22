package com.alad1nks.navigation

sealed interface NavigationItem {
    val route: String

    data object MainScreen : NavigationItem {
        override val route: String
            get() = "main"
    }

    data object RegistrationScreen : NavigationItem {
        override val route: String
            get() = "registration"
    }
}