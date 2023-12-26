package com.alad1nks.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alad1nks.core.database.dao.BusScheduleDao

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class DubovozkiDatabase : RoomDatabase() {
    abstract fun busScheduleDao(): BusScheduleDao
}