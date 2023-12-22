package com.alad1nks.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alad1nks.navigation.bottom.BottomNavigationGraph

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
            BottomNavigationGraph()
        }
    }
}