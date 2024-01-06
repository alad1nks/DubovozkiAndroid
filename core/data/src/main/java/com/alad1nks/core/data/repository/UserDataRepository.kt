package com.alad1nks.core.data.repository

import com.alad1nks.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    suspend fun changeDarkMode(isActive: Boolean)

    suspend fun changeDynamicColor(isActive: Boolean)
}