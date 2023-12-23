package com.alad1nks.feature.bus_schedule

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alad1nks.components.spinner.MenuItem
import com.alad1nks.components.spinner.Spinner

@Composable
fun BusScheduleScreen() {
}

@Composable
fun FilterSpinner(
    onSelect: (MenuItem) -> Unit,
    selectedItem: MenuItem,
    items: List<MenuItem>
) {
    var stationsExpanded by remember { mutableStateOf(false) }
    Spinner(
        expanded = stationsExpanded,
        onDismissRequest = {  },
        onClick = { stationsExpanded = true },
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
            .padding(end = 16.dp)
            .clip(MaterialTheme.shapes.small),
        selectedItem = selectedItem,
        items = items,
        onSelect = { item ->
            onSelect(item)
            stationsExpanded = false
        }
    )
}

