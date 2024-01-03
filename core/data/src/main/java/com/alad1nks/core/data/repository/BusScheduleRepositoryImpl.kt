package com.alad1nks.core.data.repository

import com.alad1nks.core.database.dao.BusScheduleDao
import com.alad1nks.core.database.entity.BusEntity
import com.alad1nks.core.database.entity.asExternalModel
import com.alad1nks.core.model.BusSchedule
import com.alad1nks.core.model.RevisionResponse
import com.alad1nks.core.network.NetworkDataSource
import com.alad1nks.core.network.model.BusResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

class BusScheduleRepositoryImpl @Inject constructor(
    private val dao: BusScheduleDao,
    private val dataSource: NetworkDataSource
) : BusScheduleRepository {
    override fun getTodayBusSchedule(
        day: Int,
        station: String
    ): Flow<BusSchedule> =
        getBusScheduleFromDao(day, station).map { pairedEntities ->
            val currentTime = (Calendar.getInstance().timeInMillis + 10800000) % 86400000
            val moscowSchedule = pairedEntities.first.asExternalModel(currentTime)
            val dubkiSchedule = pairedEntities.second.asExternalModel(currentTime)
            BusSchedule(
                moscowTopBusIndex = moscowSchedule.first,
                moscow = moscowSchedule.second,
                dubkiTopBusIndex = dubkiSchedule.first,
                dubki = dubkiSchedule.second,
            )
        }

    override fun getBusSchedule(
        day: Int,
        station: String
    ): Flow<BusSchedule> =
        getBusScheduleFromDao(day, station).map { pairedEntities ->
            BusSchedule(
                moscow = pairedEntities.first.map { it.asExternalModel() },
                dubki = pairedEntities.second.map { it.asExternalModel() }
            )
        }

    override suspend fun refreshBusSchedule(): RevisionResponse {
        val response = dataSource.getBusSchedule()
        dao.clearSchedule()
        dao.updateSchedule(response.busList.map { it.toEntity() })
        return RevisionResponse.NOT_EQUALS
    }

    private fun getBusScheduleFromDao(
        day: Int,
        station: String
    ): Flow<Pair<List<BusEntity>, List<BusEntity>>> =
        combine(
            dao.getMoscowBusEntities(day, station),
            dao.getDubkiBusEntities(day, station)
        ) { moscowBusList, dubkiBusList ->
            moscowBusList to dubkiBusList
        }

    private fun BusResponse.toEntity(): BusEntity = BusEntity(
        id = id,
        day = dayOfWeek,
        dayTime = dayTime,
        dayTimeString = dayTimeString,
        direction = direction,
        station = station
    )
}