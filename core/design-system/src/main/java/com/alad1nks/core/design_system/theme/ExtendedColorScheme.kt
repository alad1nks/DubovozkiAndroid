package com.alad1nks.core.design_system.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColorScheme(
    val odintsovo: Color = Color.Unspecified,
    val slavyanka: Color = Color.Unspecified,
    val molodyozhnaya: Color = Color.Unspecified
)

val LocalExtendedColorScheme = staticCompositionLocalOf { ExtendedColorScheme() }