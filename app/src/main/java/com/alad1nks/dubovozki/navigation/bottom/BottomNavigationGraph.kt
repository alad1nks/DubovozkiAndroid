package com.alad1nks.dubovozki.navigation.bottom

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alad1nks.feature.bus_schedule.BusScheduleScreen
import com.alad1nks.feature.settings.SettingsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationGraph(
    bottomNavController: NavHostController = rememberNavController(),
    startDestination: String = BottomNavigationItem.BusScheduleScreen.route,
) {
    val items = listOf(
        BottomNavigationItem.BusScheduleScreen,
        BottomNavigationItem.ServicesScreen,
        BottomNavigationItem.SettingsScreen
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    val selected = index == selectedIndex
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            selectedIndex = index
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
            composable(BottomNavigationItem.BusScheduleScreen.route) {
                BusScheduleScreen()
            }
            composable(BottomNavigationItem.ServicesScreen.route) {
            }
            composable(BottomNavigationItem.SettingsScreen.route) {
                SettingsScreen()
            }
        }
    }
}