package com.alad1nks.core.domain

import com.alad1nks.core.data.repository.BusScheduleRepository
import com.alad1nks.core.model.BusSchedule
import kotlinx.coroutines.flow.Flow
import java.util.Calendar
import javax.inject.Inject

class GetBusScheduleUseCase @Inject constructor(
    private val repository: BusScheduleRepository
) {
    operator fun invoke(
        day: String,
        station: String
    ): Flow<BusSchedule> {
        return when (day) {
            ScheduleDay.TODAY -> repository.getTodayBusSchedule(day.asQuery(), station)
            else -> repository.getBusSchedule(day.asQuery(), station)
        }
    }

    private fun String.asQuery(): Int {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        return when(this) {
            ScheduleDay.TODAY -> when(today) {
                Calendar.MONDAY -> DayOfTheWeek.MONDAY
                Calendar.SATURDAY -> DayOfTheWeek.SATURDAY
                Calendar.SUNDAY -> DayOfTheWeek.SUNDAY
                else -> DayOfTheWeek.WEEKDAY
            }
            ScheduleDay.TOMORROW -> when(today) {
                Calendar.FRIDAY -> DayOfTheWeek.SATURDAY
                Calendar.SATURDAY -> DayOfTheWeek.SUNDAY
                Calendar.SUNDAY -> DayOfTheWeek.MONDAY
                else -> DayOfTheWeek.WEEKDAY
            }
            ScheduleDay.WEEKDAY -> DayOfTheWeek.WEEKDAY
            ScheduleDay.SATURDAY -> DayOfTheWeek.SATURDAY
            ScheduleDay.SUNDAY -> DayOfTheWeek.SUNDAY
            else -> throw Exception("Wrong query: $this")
        }
    }

    private object DayOfTheWeek {
        const val SUNDAY = 1
        const val MONDAY = 2
        const val SATURDAY = 7
        const val WEEKDAY = 3
    }

    object ScheduleDay {
        const val TODAY = "tod"
        const val TOMORROW = "tom"
        const val WEEKDAY = "wkd"
        const val SATURDAY = "std"
        const val SUNDAY = "snd"
    }
}