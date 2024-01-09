package com.alad1nks.feature.castellan

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object CastellanDefaults {
    val firstBuildingGraphic = listOf(
        GraphicItem(2, "ПН", "9:30-20:45"),
        GraphicItem(3, "ВТ", "9:30-17:45"),
        GraphicItem(4, "СР", "9:30-17:45"),
        GraphicItem(5, "ЧТ", "9:30-20:45"),
        GraphicItem(6, "ПТ", "9:30-17:45"),
        GraphicItem(7, "СБ", "13:30-14:00"),
        GraphicItem(1, "ВС", "Выходной")
    )

    val otherBuildingGraphic = listOf(
        GraphicItem(2, "ПН", "9:30-17:45"),
        GraphicItem(3, "ВТ", "9:30-17:45"),
        GraphicItem(4, "СР", "Прием и отправка белья"),
        GraphicItem(5, "ЧТ", "14:00-21:45"),
        GraphicItem(6, "ПТ", "9:30-17:45"),
        GraphicItem(7, "СБ", "13:30-14:00"),
        GraphicItem(1, "ВС", "Выходной")
    )

    private val CurrentContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primaryContainer

    private val CurrentTextColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimaryContainer

    private val ContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.tertiaryContainer

    private val TextColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onTertiaryContainer

    @Composable
    fun colors(
        current: Boolean
    ): DayColor =
        if (current) DayColor(CurrentContainerColor, CurrentTextColor)
        else DayColor(ContainerColor, TextColor)
}

data class GraphicItem(
    val calendar: Int,
    val title: String,
    val time: String
)

data class DayColor(
    val container: Color,
    val text: Color
)
