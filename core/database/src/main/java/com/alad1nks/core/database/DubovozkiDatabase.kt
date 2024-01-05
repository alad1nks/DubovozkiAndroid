package com.alad1nks.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alad1nks.core.database.dao.BusScheduleDao
import com.alad1nks.core.database.entity.BusEntity

@Database(
    entities = [
        BusEntity::class
               ],
    version = 1,
    exportSchema = false
)
abstract class DubovozkiDatabase : RoomDatabase() {
    abstract fun busScheduleDao(): BusScheduleDao
}