package com.alad1nks.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.alad1nks.core.model.UserData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val USER_PREFERENCES_NAME = "dubovozki_datastore"

@Singleton
class LocalDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStore {

    private val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES_NAME
    )
    override fun userPreferencesFlow(): Flow<UserData> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val darkTheme = preferences[PreferencesKeys.DARK_THEME] ?: false
                val dynamicColor = preferences[PreferencesKeys.DYNAMIC_COLOR] ?: false
                val revision = preferences[PreferencesKeys.REVISION] ?: 0
                UserData(
                    darkTheme = darkTheme,
                    dynamicColor = dynamicColor,
                    revision = revision
                )
            }

    override suspend fun getRevision(): Int = userPreferencesFlow().first().revision

    override suspend fun updateRevision(revision: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.REVISION] = revision
        }
    }
}