package com.alad1nks.core.model

sealed interface Bus {

    data class Upcoming(
        val id: Int,
        val time: String,
        val timeLeft: String,
        val soon: Boolean,
        val station: Station
    ) : Bus

    data class Departed(
        val id: Int,
        val time: String,
        val timePassed: String,
        val station: Station
    ) : Bus

    data class Boyish(
        val id: Int,
        val time: String,
        val station: Station
    ) : Bus
}