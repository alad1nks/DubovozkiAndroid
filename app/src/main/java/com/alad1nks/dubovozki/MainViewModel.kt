package com.alad1nks.dubovozki

import androidx.lifecycle.ViewModel
import com.alad1nks.core.data.repository.UserDataRepository
import com.alad1nks.core.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    userDataRepository: UserDataRepository
) : ViewModel() {
    val uiState: Flow<UserData> = userDataRepository.userData
}