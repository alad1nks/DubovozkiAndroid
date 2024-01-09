package com.alad1nks.feature.castellan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.LooksOne
import androidx.compose.material.icons.outlined.LooksTwo
import androidx.compose.material.icons.rounded.Looks3
import androidx.compose.material.icons.rounded.LooksOne
import androidx.compose.material.icons.rounded.LooksTwo
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alad1nks.feature.castellan.CastellanDefaults.colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastellanScreen(
    navController: NavHostController,
    viewModel: CastellanViewModel = hiltViewModel()
) {
    val menuItems = listOf("1 корпус", "2 корпус", "3 корпус")
    val buildings = listOf(
        Building.First,
        Building.Second,
        Building.Third
    )
    val graphic by viewModel.graphic.collectAsState()
    val day by viewModel.day.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedBuildingIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = "Расписание кастелянной")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                actions = {
                    buildings.forEachIndexed { index, building ->
                        Icon(
                            imageVector = building.icon(selectedBuildingIndex == index),
                            contentDescription = null
                        )
                    }
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            menuItems.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = {
                                        selectedBuildingIndex = index
                                        viewModel.updateBuilding(index)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            item {
                graphic.forEach { item ->
                    ListItem(
                        headlineContent = { Text(text = item.time, fontSize = 24.sp) },
                        leadingContent = {
                            DayBox(
                                text = item.title,
                                current = item.calendar == day
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun DayBox(
    text: String,
    current: Boolean,
    colors: DayColor = colors(current)
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .background(colors.container, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = colors.text
        )
    }
}

private sealed class Building(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object First : Building(
        selectedIcon = Icons.Rounded.LooksOne,
        unselectedIcon = Icons.Outlined.LooksOne
    )

    data object Second : Building(
        selectedIcon = Icons.Rounded.LooksTwo,
        unselectedIcon = Icons.Outlined.LooksTwo
    )

    data object Third : Building(
        selectedIcon = Icons.Rounded.Looks3,
        unselectedIcon = Icons.Outlined.Looks3
    )

    fun icon(selected: Boolean): ImageVector {
        return if(selected) selectedIcon else unselectedIcon
    }
}
