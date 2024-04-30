package com.alad1nks.core.network

import com.alad1nks.core.network.model.BusScheduleResponse
import com.alad1nks.core.network.model.BusScheduleRevisionResponse

interface NetworkDataSource {
    suspend fun getBusSchedule(): BusScheduleResponse
    suspend fun getBusScheduleRevision(): BusScheduleRevisionResponse
}
