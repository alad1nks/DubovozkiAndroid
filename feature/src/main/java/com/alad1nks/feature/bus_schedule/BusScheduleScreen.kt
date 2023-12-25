package com.alad1nks.feature.bus_schedule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alad1nks.core.design_system.MenuItem
import com.alad1nks.core.design_system.Spinner
import com.alad1nks.core.model.BusSchedule
import com.alad1nks.core.ui.BusScheduleScreenState
import com.alad1nks.core.ui.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbar
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import me.onebone.toolbar.rememberCollapsingToolbarState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BusScheduleScreen(
    viewModel: BusScheduleViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val stations = listOf(
        MenuItem("Все станции", "all"),
        MenuItem("Одинцово", "odn"),
        MenuItem("Молодежная", "mld"),
        MenuItem("Славянский б-р", "slv")
    )
    val directions = listOf(
        MenuItem("В Москву", "msk"),
        MenuItem("В Дубки", "dbk")
    )
    val days = listOf(
        MenuItem("Сегодня", "tod"),
        MenuItem("Завтра", "tom"),
        MenuItem("Будни", "wkd"),
        MenuItem("Суббота", "std"),
        MenuItem("Воскресенье", "snd")
    )

    val screenState by viewModel.screenState.collectAsState()
    val queryState by viewModel.queryState.collectAsState()

    val scaffoldState = rememberCollapsingToolbarScaffoldState()
    val toolbarState = rememberCollapsingToolbarState()
    val pagerState = rememberPagerState { 2 }
    val selectedTabIndex = pagerState.currentPage

    val coroutineScope = rememberCoroutineScope()
    
    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = scaffoldState,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            CollapsingToolbar(
                modifier = Modifier.background(MaterialTheme.colorScheme.inversePrimary),
                collapsingToolbarState = toolbarState
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    FilterSpinner(
                        onSelect = {  station ->
                            viewModel.updateStation(station)
                        },
                        modifier = Modifier.weight(1f),
                        selectedItem = queryState.station,
                        items = stations
                    )
                    FilterSpinner(
                        onSelect = { day ->
                            viewModel.updateDay(day)
                        },
                        modifier = Modifier.weight(1f),
                        selectedItem = queryState.day,
                        items = days
                    )
                }
            }
        }
    ) {

        Column(modifier = Modifier) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                directions.forEachIndexed { index, direction ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = {
                            coroutineScope.launch(Dispatchers.IO) {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(text = direction.title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                    )
                }
            }
            screenState.Content(pagerState = pagerState, snackbarHostState = snackbarHostState)
        }
    }
}

@Composable
private fun FilterSpinner(
    onSelect: (MenuItem) -> Unit,
    modifier: Modifier,
    selectedItem: MenuItem,
    items: List<MenuItem>
) {
    var stationsExpanded by remember { mutableStateOf(false) }
    Spinner(
        expanded = stationsExpanded,
        onDismissRequest = { },
        onClick = { stationsExpanded = true },
        modifier = modifier
            .padding(16.dp)
            .clip(MaterialTheme.shapes.small),
        selectedItem = selectedItem,
        items = items,
        onSelect = { item ->
            onSelect(item)
            stationsExpanded = false
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BusScheduleScreenState.Content(
    pagerState: PagerState,
    snackbarHostState: SnackbarHostState
) {
    when(this) {
        is BusScheduleScreenState.Init -> {
            HorizontalPager(
                state = pagerState
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        is BusScheduleScreenState.Loading -> {
            Schedule(pagerState = pagerState, schedule = schedule)
        }
        is BusScheduleScreenState.Data -> {
            Schedule(pagerState = pagerState, schedule = schedule)
        }
        is BusScheduleScreenState.NetworkError -> {
            LaunchedEffect(snackbarHostState) {
                val snackbarResult = snackbarHostState.showSnackbar(
                    message = "Проблемы с соединением",
                    actionLabel = "Повторить",
                    withDismissAction = true
                )
                when(snackbarResult) {
                    SnackbarResult.Dismissed -> {

                    }
                    SnackbarResult.ActionPerformed -> {

                    }
                }
            }
            Schedule(pagerState = pagerState, schedule = schedule)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Schedule(
    pagerState: PagerState,
    schedule: BusSchedule
) {
    val moscowBusListState = rememberLazyListState(
        initialFirstVisibleItemIndex = schedule.moscowTopBusIndex
    )
    val dubkiBusListState = rememberLazyListState(
        initialFirstVisibleItemIndex = schedule.dubkiTopBusIndex
    )
    HorizontalPager(
        state = pagerState
    ) { page ->
        when(page) {
            0 -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = moscowBusListState
                ) {
                    items(schedule.moscow) {bus ->
                        bus.Content()
                    }
                }
            }
            1 -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = dubkiBusListState
                ) {
                    items(schedule.dubki) {bus ->
                        bus.Content()
                    }
                }
            }
        }
    }
}
