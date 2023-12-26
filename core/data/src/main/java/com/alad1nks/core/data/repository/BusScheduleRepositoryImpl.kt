package com.alad1nks.core.data.repository

import com.alad1nks.core.model.Bus
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusScheduleRepositoryImpl @Inject constructor(

) : BusScheduleRepository {
    override fun getBusSchedule(day: Int, station: String?): Flow<List<Bus>> {
        TODO("Not yet implemented")
    }
}