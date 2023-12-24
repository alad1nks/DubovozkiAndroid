package com.alad1nks.core.model

data class BusSchedule(
    val moscowTopBusIndex: Int,
    val moscow: List<Bus>,
    val dubkiTopBusIndex: Int,
    val dubki: List<Bus>
)