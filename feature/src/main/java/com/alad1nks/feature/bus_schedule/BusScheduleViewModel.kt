package com.alad1nks.feature.bus_schedule

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alad1nks.core.design_system.model.MenuItem
import com.alad1nks.core.domain.GetBusScheduleUseCase
import com.alad1nks.core.domain.RefreshBusScheduleUseCase
import com.alad1nks.core.model.BusSchedule
import com.alad1nks.core.model.RevisionResponse
import com.alad1nks.core.ui.BusScheduleQueryState
import com.alad1nks.core.ui.BusScheduleScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusScheduleViewModel @Inject constructor(
    private val getBusScheduleUseCase: GetBusScheduleUseCase,
    private val refreshBusScheduleUseCase: RefreshBusScheduleUseCase
) : ViewModel() {
    private val _screenState: MutableStateFlow<BusScheduleScreenState> =
        MutableStateFlow(BusScheduleScreenState.Init)
    val screenState: StateFlow<BusScheduleScreenState> get() = _screenState.asStateFlow()

    private val _queryState: MutableStateFlow<BusScheduleQueryState> =
        MutableStateFlow(BusScheduleQueryState())
    val queryState: StateFlow<BusScheduleQueryState> get() = _queryState.asStateFlow()

    private val handler = Handler(Looper.getMainLooper())
    private val updateTask = object : Runnable {
        override fun run() {
            handler.postDelayed(this, 30_000)
            viewModelScope.launch(Dispatchers.IO) {
                offlineRefreshBusScheduleScreenState(_queryState.value)
            }
        }
    }

    init {
        handler.post(updateTask)
        subscribeToQueryChanges()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun subscribeToQueryChanges() {
        _queryState
            .flatMapConcat { flow { emit(getBusScheduleScreenState(it)) } }
            .onEach {
                _screenState.emit(it)
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private suspend fun getBusScheduleScreenState(
        query: BusScheduleQueryState
    ): BusScheduleScreenState {
        _screenState.emit(BusScheduleScreenState.Loading(getBusSchedule(query)))
        val revisionResponse = refreshBusScheduleUseCase()
        return revisionResponse.screenState(getBusSchedule(query))
    }

    private suspend fun offlineRefreshBusScheduleScreenState(
        query: BusScheduleQueryState
    ) {
        _screenState.emit(BusScheduleScreenState.Data(getBusSchedule(query)))
    }

    private suspend fun getBusSchedule(
        query: BusScheduleQueryState
    ): BusSchedule = getBusScheduleUseCase(
        query.day.route, query.station.route
    ).first()

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

    private fun RevisionResponse.screenState(
        schedule: BusSchedule
    ): BusScheduleScreenState {
        return when(this) {
            RevisionResponse.NETWORK_ERROR -> BusScheduleScreenState.NetworkError(schedule)
            else -> BusScheduleScreenState.Data(schedule)
        }
    }
}