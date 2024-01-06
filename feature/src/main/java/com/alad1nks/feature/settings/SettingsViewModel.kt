package com.alad1nks.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alad1nks.core.data.repository.UserDataRepository
import com.alad1nks.core.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    val uiState: Flow<UserData> = userDataRepository.userData

    fun changeDarkMode(
        dark: Boolean
    ) {
        viewModelScope.launch {
            userDataRepository.changeDarkMode(dark)
        }
    }

    fun changeDynamicColor(
        dynamic: Boolean
    ) {
        viewModelScope.launch {
            userDataRepository.changeDynamicColor(dynamic)
        }
    }
}