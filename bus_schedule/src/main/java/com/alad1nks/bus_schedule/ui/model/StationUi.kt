package com.alad1nks.bus_schedule.ui.model

import androidx.compose.runtime.Composable

sealed interface StationUi {
    fun timeContent(time: String): @Composable () -> Unit
    fun nameContent(): @Composable () -> Unit
    fun nameContentDeparted(): @Composable () -> Unit
}