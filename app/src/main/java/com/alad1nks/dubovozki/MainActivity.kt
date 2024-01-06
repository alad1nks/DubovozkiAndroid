package com.alad1nks.dubovozki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.alad1nks.core.design_system.theme.DubovozkiAndroidTheme
import com.alad1nks.core.model.UserData
import com.alad1nks.dubovozki.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState by viewModel.uiState.collectAsState(
                initial = UserData(
                    darkTheme = false,
                    dynamicColor = false,
                    revision = 0
                )
            )
            DubovozkiAndroidTheme(
                darkTheme = uiState.darkTheme,
                dynamicColor = uiState.dynamicColor
            ) {
                NavigationGraph()
            }
        }
    }
}