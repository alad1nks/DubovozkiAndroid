package com.alad1nks.dubovozki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.alad1nks.components.spinner.MenuItem
import com.alad1nks.components.spinner.Spinner
import com.alad1nks.dubovozki.ui.theme.DubovozkiAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DubovozkiAndroidTheme {
                var expanded by remember { mutableStateOf(false) }
                val items = listOf(
                    MenuItem("SAS", "sas"),
                    MenuItem("safsaf", "agags")
                )
                var selectedItem by remember { mutableStateOf(items.first()) }
                Spinner(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    onClick = {
                        expanded = !expanded
                    },
                    items = items,
                    selectedItem = selectedItem,
                    onSelect = {
                        selectedItem = it
                        expanded = false
                    }
                )
            }
        }
    }
}