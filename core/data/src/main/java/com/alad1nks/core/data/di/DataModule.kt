package com.alad1nks.core.data.di

import com.alad1nks.core.data.repository.BusScheduleRepository
import com.alad1nks.core.data.repository.BusScheduleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsBusScheduleRepository(
        topicsRepository: BusScheduleRepositoryImpl,
    ): BusScheduleRepository
}