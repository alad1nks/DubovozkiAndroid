package com.alad1nks.feature.bus_schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alad1nks.core.design_system.MenuItem
import com.alad1nks.core.domain.GetBusScheduleUseCase
import com.alad1nks.core.ui.BusScheduleQueryState
import com.alad1nks.core.ui.BusScheduleScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class BusScheduleViewModel @Inject constructor(
    getBusScheduleUseCase: GetBusScheduleUseCase
) : ViewModel() {
    private val _queryState: MutableStateFlow<BusScheduleQueryState> =
        MutableStateFlow(BusScheduleQueryState())
    val queryState: StateFlow<BusScheduleQueryState> get() = _queryState.asStateFlow()

    @OptIn(FlowPreview::class)
    val screenState: StateFlow<BusScheduleScreenState> =
        _queryState.flatMapConcat {
            flowOf(
                BusScheduleScreenState.Data(
                    getBusScheduleUseCase(it.day.route, it.station.route).first()
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BusScheduleScreenState.Init,
        )


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