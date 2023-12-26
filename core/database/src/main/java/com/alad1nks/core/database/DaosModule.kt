package com.alad1nks.core.database

import com.alad1nks.core.database.dao.BusScheduleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: DubovozkiDatabase,
    ): BusScheduleDao = database.busScheduleDao()
}