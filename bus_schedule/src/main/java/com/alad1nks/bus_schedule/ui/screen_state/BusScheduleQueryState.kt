package com.alad1nks.bus_schedule.ui.screen_state

import com.alad1nks.components.spinner.MenuItem

data class BusScheduleQueryState(
    val day: MenuItem = MenuItem("Сегодня", "tod"),
    val station: MenuItem = MenuItem("Все станции", "all")
)