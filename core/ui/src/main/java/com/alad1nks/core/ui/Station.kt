package com.alad1nks.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.alad1nks.core.design_system.theme.LocalExtendedColorScheme
import com.alad1nks.core.model.Station

@Composable
fun Station.timeContent(time: String): @Composable () -> Unit = {
    when(this) {
        Station.ODINTSOVO ->
            Text(
                text = time,
                color = color,
                fontSize = 24.sp
            )

        Station.SLAVYANKA ->
            Text(
                text = time,
                color = color,
                fontSize = 24.sp
            )

        Station.MOLODYOZHNAYA ->
            Text(
                text = time,
                color = color,
                fontSize = 24.sp
            )
    }
}

@Composable
fun Station.nameContent(): @Composable () -> Unit = {
    when(this) {
        Station.ODINTSOVO ->
            Text(
                text = title,
                fontSize = 16.sp
            )

        Station.SLAVYANKA ->
            Text(
                text = title,
                color = color,
                fontSize = 16.sp
            )

        Station.MOLODYOZHNAYA ->
            Text(
                text = title,
                color = color,
                fontSize = 16.sp
            )
    }
}

@Composable
fun Station.nameContentDeparted(): @Composable () -> Unit = {
    when(this) {
        Station.ODINTSOVO ->
            Text(
                text = title,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 16.sp
            )

        Station.SLAVYANKA ->
            Text(
                text = title,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 16.sp
            )

        Station.MOLODYOZHNAYA ->
            Text(
                text = title,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 16.sp
            )
    }
}

private val Station.title: String
    get() = when(this) {
        Station.ODINTSOVO -> "Одинцово"
        Station.SLAVYANKA -> "Славянский б-р"
        Station.MOLODYOZHNAYA -> "Молодёжная"
    }

private val Station.color: Color
    @Composable
    get() =
        when(this) {
            Station.ODINTSOVO -> LocalExtendedColorScheme.current.odintsovo
            Station.SLAVYANKA -> LocalExtendedColorScheme.current.slavyanka
            Station.MOLODYOZHNAYA -> LocalExtendedColorScheme.current.molodyozhnaya
        }