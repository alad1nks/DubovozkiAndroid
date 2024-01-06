package com.alad1nks.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alad1nks.core.model.UserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(
        UserData(
            darkTheme = false,
            dynamicColor = false,
            revision = 0
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Настройки",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "Оформление",
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
            ListItem(
                headlineContent = {
                    Text("Тёмная тема")
                },
                trailingContent = {
                    Switch(
                        checked = uiState.darkTheme,
                        onCheckedChange = { checked ->
                            viewModel.changeDarkMode(checked)
                        }
                    )
                }
            )
            ListItem(
                headlineContent = {
                    Text("Динамические цвета")
                },
                trailingContent = {
                    Switch(
                        checked = uiState.dynamicColor,
                        onCheckedChange = { checked ->
                            viewModel.changeDynamicColor(checked)
                        }
                    )
                }
            )
        }
    }
}