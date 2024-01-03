package com.alad1nks.core.data.repository

import com.alad1nks.core.model.BusSchedule
import com.alad1nks.core.model.RevisionResponse
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

    suspend fun refreshBusSchedule(): RevisionResponse
}