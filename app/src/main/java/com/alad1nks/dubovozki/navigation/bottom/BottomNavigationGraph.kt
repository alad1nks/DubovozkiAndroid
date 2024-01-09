package com.alad1nks.dubovozki.navigation.bottom

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alad1nks.feature.bus_schedule.BusScheduleScreen
import com.alad1nks.feature.services.ServicesScreen
import com.alad1nks.feature.settings.SettingsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationGraph(
    bottomNavController: NavHostController = rememberNavController(),
    navController: NavHostController,
    startDestination: String = BottomNavigationItem.BusScheduleScreen.route,
) {
    val items = listOf(
        BottomNavigationItem.ServicesScreen,
        BottomNavigationItem.BusScheduleScreen,
        BottomNavigationItem.SettingsScreen
    )

    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    val selected = currentRoute == item.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                bottomNavController.graph.startDestinationRoute?.let { screenRoute ->
                                    popUpTo(screenRoute) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                                  },
                        icon = { item.icon.draw(selected) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavigationItem.ServicesScreen.route) {
                ServicesScreen(navController = navController)
            }
            composable(BottomNavigationItem.BusScheduleScreen.route) {
                BusScheduleScreen()
            }
            composable(BottomNavigationItem.SettingsScreen.route) {
                SettingsScreen()
            }
        }
    }
}