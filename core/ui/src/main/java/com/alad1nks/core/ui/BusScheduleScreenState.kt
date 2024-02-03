package com.alad1nks.core.ui

import com.alad1nks.core.design_system.model.MenuItem
import com.alad1nks.core.model.BusSchedule

data class BusScheduleScreenState(
    val state: State = State.INIT,
    val schedule: BusSchedule = BusSchedule()
)

data class BusScheduleQueryState(
    val day: MenuItem = MenuItem("Сегодня", "tod"),
    val station: MenuItem = MenuItem("Все станции", "%%")
)
