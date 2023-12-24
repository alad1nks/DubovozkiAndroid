package com.alad1nks.feature.bus_schedule

import androidx.lifecycle.ViewModel
import com.alad1nks.core.design_system.MenuItem
import com.alad1nks.core.ui.BusScheduleQueryState
import com.alad1nks.core.ui.BusScheduleScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BusScheduleViewModel @Inject constructor(

) : ViewModel() {
    private val _screenState: MutableStateFlow<BusScheduleScreenState> =
        MutableStateFlow(BusScheduleScreenState.Init)
    val screenState: StateFlow<BusScheduleScreenState> get() = _screenState.asStateFlow()

    private val _queryState: MutableStateFlow<BusScheduleQueryState> =
        MutableStateFlow(BusScheduleQueryState())
    val queryState: StateFlow<BusScheduleQueryState> get() = _queryState.asStateFlow()

    fun updateDay(day: MenuItem) {
        _queryState.update {
            it.copy(day = day)
        }
    }

    fun updateStation(station: MenuItem) {
        _queryState.update {
            it.copy(station = station)
        }
    }
}