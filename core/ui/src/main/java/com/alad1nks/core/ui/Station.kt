package com.alad1nks.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.alad1nks.core.model.Station

val Station.color: Color
    get() =
        when(this) {
            Station.ODINTSOVO -> Color.Black
            Station.SLAVYANKA -> Color.Green
            Station.MOLODYOZHNAYA -> Color.Green
        }

@Composable
fun Station.timeContent(time: String): @Composable () -> Unit = {
    when(this) {
        Station.ODINTSOVO ->
            Text(
                text = time,
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
                text = "Одинцово",
                fontSize = 16.sp
            )

        Station.SLAVYANKA ->
            Text(
                text = "Славянский б-р",
                color = color,
                fontSize = 16.sp
            )

        Station.MOLODYOZHNAYA ->
            Text(
                text = "Молодёжная",
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
                text = "Одинцово",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 16.sp
            )

        Station.SLAVYANKA ->
            Text(
                text = "Славянский б-р",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 16.sp
            )

        Station.MOLODYOZHNAYA ->
            Text(
                text = "Молодёжная",
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontSize = 16.sp
            )
    }
}