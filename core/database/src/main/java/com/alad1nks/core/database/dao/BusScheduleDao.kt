package com.alad1nks.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alad1nks.core.database.entity.BusEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {
    @Query("SELECT * FROM bus_schedule WHERE direction = 'msk' and day = :day and station = :station")
    fun getMoscowBusList(day: Int, station: String): Flow<List<BusEntity>>

    @Query("SELECT * FROM bus_schedule WHERE direction = 'dbk' and day = :day and station = :station")
    fun getDubkiBusList(day: Int, station: String): Flow<List<BusEntity>>

    @Query("SELECT * FROM bus_schedule WHERE direction = 'msk' and day = :day")
    fun getMoscowBusListAllStation(day: Int): Flow<List<BusEntity>>

    @Query("SELECT * FROM bus_schedule WHERE direction = 'dbk' and day = :day")
    fun getDubkiBusListAllStation(day: Int): Flow<List<BusEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun updateSchedule(schedule: List<BusEntity>)

    @Query("DELETE FROM bus_schedule")
    fun clearSchedule()
}