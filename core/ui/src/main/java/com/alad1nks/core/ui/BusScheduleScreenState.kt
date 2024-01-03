package com.alad1nks.core.ui

import com.alad1nks.core.design_system.model.MenuItem
import com.alad1nks.core.model.BusSchedule

sealed interface BusScheduleScreenState {
    data object Init : BusScheduleScreenState

    data class Loading(
        val schedule: BusSchedule
    ) : BusScheduleScreenState

    data class Data(
        val schedule: BusSchedule
    ) : BusScheduleScreenState

    data class NetworkError(
        val schedule: BusSchedule
    ) : BusScheduleScreenState
}

data class BusScheduleQueryState(
    val day: MenuItem = MenuItem("Сегодня", "tod"),
    val station: MenuItem = MenuItem("Все станции", "%%")
)