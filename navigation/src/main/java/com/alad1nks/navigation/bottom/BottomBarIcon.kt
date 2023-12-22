package com.alad1nks.navigation.bottom

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarIcon(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {

    val draw: @Composable (Boolean) -> Unit
        get() = { selected ->
            if (selected) Icon(selectedIcon, null)
            else Icon(unselectedIcon, null)
        }
}