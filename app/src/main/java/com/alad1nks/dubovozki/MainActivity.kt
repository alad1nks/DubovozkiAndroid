package com.alad1nks.dubovozki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alad1nks.dubovozki.navigation.NavigationGraph
import com.alad1nks.dubovozki.ui.theme.DubovozkiAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DubovozkiAndroidTheme {
                NavigationGraph()
            }
        }
    }
}