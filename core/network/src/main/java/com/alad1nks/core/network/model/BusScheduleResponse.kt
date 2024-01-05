package com.alad1nks.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BusScheduleResponse(
    val busList: List<BusResponse>,
    val revision: Int
)