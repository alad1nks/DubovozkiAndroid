package com.alad1nks.core.data.repository

import com.alad1nks.core.datastore.DataStore
import com.alad1nks.core.model.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val dataStore: DataStore
) : UserDataRepository {
    override val userData: Flow<UserData>
        get() = dataStore.userPreferencesFlow()

    override suspend fun changeDarkMode(isActive: Boolean) {
        dataStore.changeDarkMode(isActive)
    }

    override suspend fun changeDynamicColor(isActive: Boolean) {
        dataStore.changeDynamicColor(isActive)
    }
}