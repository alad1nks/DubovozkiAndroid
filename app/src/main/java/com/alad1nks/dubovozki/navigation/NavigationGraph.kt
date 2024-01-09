package com.alad1nks.dubovozki.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alad1nks.core.model.NavigationItem
import com.alad1nks.dubovozki.navigation.bottom.BottomNavigationGraph
import com.alad1nks.feature.castellan.CastellanScreen

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationItem.MainScreen.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.RegistrationScreen.route) {
        }
        composable(NavigationItem.MainScreen.route) {
            BottomNavigationGraph(navController = navController)
        }
        composable(NavigationItem.CastellanScreen.route) {
            CastellanScreen(navController = navController)
        }
    }
}