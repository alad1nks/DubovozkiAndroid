package com.alad1nks.dubovozki.navigation.bottom

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBusFilled
import androidx.compose.material.icons.filled.HolidayVillage
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.DirectionsBusFilled
import androidx.compose.material.icons.outlined.HolidayVillage
import androidx.compose.material.icons.outlined.Settings

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
                selectedIcon = Icons.Filled.DirectionsBusFilled,
                unselectedIcon = Icons.Outlined.DirectionsBusFilled
            )
    }

    data object ServicesScreen : BottomNavigationItem {
        override val route: String
            get() = "services"
        override val title: String
            get() = "Главная"
        override val icon: BottomBarIcon
            get() = BottomBarIcon(
                selectedIcon = Icons.Filled.HolidayVillage,
                unselectedIcon = Icons.Outlined.HolidayVillage
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