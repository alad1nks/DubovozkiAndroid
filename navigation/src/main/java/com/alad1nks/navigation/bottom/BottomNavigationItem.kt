package com.alad1nks.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DepartureBoard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.DepartureBoard
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Widgets

sealed interface BottomNavigationItem {
    val route: String
    val title: String
    val icon: BottomBarIcon

    data object BusScheduleScreen : BottomNavigationItem {
        override val route: String
            get() = "schedule"
        override val title: String
            get() = "Расписание"
        override val icon: BottomBarIcon
            get() = BottomBarIcon(
                selectedIcon = Icons.Filled.DepartureBoard,
                unselectedIcon = Icons.Outlined.DepartureBoard
            )
    }

    data object ServicesScreen : BottomNavigationItem {
        override val route: String
            get() = "services"
        override val title: String
            get() = "Сервисы"
        override val icon: BottomBarIcon
            get() = BottomBarIcon(
                selectedIcon = Icons.Filled.Widgets,
                unselectedIcon = Icons.Outlined.Widgets
            )
    }

    data object SettingsScreen : BottomNavigationItem {
        override val route: String
            get() = "settings"
        override val title: String
            get() = "Настройки"
        override val icon: BottomBarIcon
            get() = BottomBarIcon(
                selectedIcon = Icons.Filled.Settings,
                unselectedIcon = Icons.Outlined.Settings
            )
    }
}