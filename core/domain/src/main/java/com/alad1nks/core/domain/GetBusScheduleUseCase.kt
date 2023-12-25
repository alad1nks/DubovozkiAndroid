package com.alad1nks.core.domain

import com.alad1nks.core.model.Bus
import com.alad1nks.core.model.BusSchedule
import com.alad1nks.core.model.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class GetBusScheduleUseCase @Inject constructor(

) {
    operator fun invoke(
        day: String,
        station: String
    ): Flow<BusSchedule> = when(station) {
        "odn" -> MutableStateFlow(BusSchedule(
                    0,
                    listOf(Bus.Boyish(0, "08:35", Station.ODINTSOVO)),
                    0,
                    listOf(Bus.Boyish(0, "08:35", Station.ODINTSOVO))
                ))
        else -> MutableStateFlow(BusSchedule(0, listOf(Bus.Boyish(0, "09:35", Station.MOLODYOZHNAYA)), 0, listOf(Bus.Boyish(0, "09:35", Station.MOLODYOZHNAYA))))
    }
}