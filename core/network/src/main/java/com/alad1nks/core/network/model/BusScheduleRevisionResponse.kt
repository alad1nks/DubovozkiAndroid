package com.alad1nks.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BusScheduleRevisionResponse(
    val revision: Int
)
