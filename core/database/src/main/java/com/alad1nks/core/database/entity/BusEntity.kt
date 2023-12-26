package com.alad1nks.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alad1nks.core.model.Bus
import com.alad1nks.core.model.Station

@Entity(tableName = "bus_schedule")
data class BusEntity(
    @PrimaryKey
    val id: Int,
    val day: Int,
    val dayTime: Long,
    val dayTimeString: String,
    val direction: String,
    val station: String
)

fun BusEntity.asExternalModel(): Bus.Boyish = Bus.Boyish(
    id = id,
    time = dayTimeString,
    station = station.asExternalModel()
)

fun BusEntity.asExternalModel(
    currentTime: Long
): Bus {
    val timeLeft = (dayTime - currentTime) / 60000
    if (timeLeft < 0) {
        val minutesPassed = -timeLeft % 60
        val hoursPassed = -timeLeft / 60
        return Bus.Departed(
            id = id,
            time = dayTimeString,
            timePassed = if (hoursPassed > 0) {
                "$hoursPassed ч $minutesPassed мин назад"
            } else {
                "$minutesPassed мин назад"
            },
            station = station.asExternalModel()
        )
    } else {
        val minutesLeft = timeLeft % 60
        val hoursLeft = timeLeft / 60
        return Bus.Upcoming(
            id = id,
            time = dayTimeString,
            timeLeft = if (hoursLeft > 0) {
                "через $hoursLeft ч $minutesLeft мин"
            } else {
                "через $minutesLeft мин"
            },
            soon = timeLeft <= 15,
            station = station.asExternalModel()
        )
    }
}


private fun String.asExternalModel(): Station =
    when(this) {
        "odn" -> Station.ODINTSOVO
        "slv" -> Station.SLAVYANKA
        else -> Station.MOLODYOZHNAYA
    }
