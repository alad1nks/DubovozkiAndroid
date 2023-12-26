package com.alad1nks.core.data.repository

import com.alad1nks.core.model.Bus
import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    fun getBusSchedule(
        day: Int,
        station: String?
    ): Flow<List<Bus>>

}