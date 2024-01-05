package com.alad1nks.core.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

internal object PreferencesKeys {
    val DARK_THEME = booleanPreferencesKey("dark_theme")
    val DYNAMIC_COLOR = booleanPreferencesKey("dynamic_color")
    val REVISION = intPreferencesKey("revision")
}