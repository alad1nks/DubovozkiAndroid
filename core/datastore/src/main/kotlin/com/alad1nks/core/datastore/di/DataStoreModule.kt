package com.alad1nks.core.datastore.di

import com.alad1nks.core.datastore.LocalDataStore
import com.alad1nks.core.datastore.DataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {
    @Singleton
    @Binds
    abstract fun bindStorage(storage: LocalDataStore): DataStore
}