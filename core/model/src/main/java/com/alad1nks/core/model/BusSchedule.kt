package com.alad1nks.core.model

data class BusSchedule(
    val moscowTopBusIndex: Int = 0,
    val moscow: List<Bus> = emptyList(),
    val dubkiTopBusIndex: Int = 0,
    val dubki: List<Bus> = emptyList()
)