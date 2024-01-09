package com.alad1nks.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Настройки")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Text(
                text = "Оформление",
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            ListItem(
                modifier = Modifier
                    .clickable {
                        viewModel.changeDarkMode(!uiState.darkTheme)
                    },
                headlineContent = {
                    Text("Тёмная тема")
                },
                trailingContent = {
                    Switch(
                        checked = uiState.darkTheme,
                        onCheckedChange = null
                    )
                }
            )
            ListItem(
                modifier = Modifier
                    .clickable {
                        viewModel.changeDynamicColor(!uiState.dynamicColor)
                    },
                headlineContent = {
                    Text("Динамические цвета")
                },
                trailingContent = {
                    Switch(
                        checked = uiState.dynamicColor,
                        onCheckedChange = null
                    )
                }
            )
        }
    }
}