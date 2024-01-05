package com.alad1nks.core.network

import com.alad1nks.core.network.model.BusScheduleResponse

interface NetworkDataSource {
    suspend fun getBusSchedule(): BusScheduleResponse
}