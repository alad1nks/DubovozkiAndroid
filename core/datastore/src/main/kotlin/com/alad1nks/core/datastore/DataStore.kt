package com.alad1nks.core.datastore

import com.alad1nks.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface DataStore {
    fun userPreferencesFlow(): Flow<UserData>

    suspend fun getRevision(): Int

    suspend fun updateRevision(revision: Int)

    suspend fun changeDarkMode(isActive: Boolean)

    suspend fun changeDynamicColor(isActive: Boolean)
}