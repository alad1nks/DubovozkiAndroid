package com.alad1nks.core.data.repository

import com.alad1nks.core.model.BusSchedule
import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    fun getTodayBusSchedule(
        day: Int,
        station: String
    ): Flow<BusSchedule>

    fun getBusSchedule(
        day: Int,
        station: String
    ): Flow<BusSchedule>
}