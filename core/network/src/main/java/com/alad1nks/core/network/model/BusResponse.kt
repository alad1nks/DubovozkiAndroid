package com.alad1nks.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BusResponse(
    val id: Int,
    val dayOfWeek: Int,
    val dayTime: Long,
    val dayTimeString: String,
    val direction: String,
    val station: String
)