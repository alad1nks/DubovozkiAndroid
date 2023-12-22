package com.alad1nks.bus_schedule.ui.screen_state

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

sealed interface BusScheduleScreenState {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Content(pagerState: PagerState, snackbarHostState: SnackbarHostState)
}