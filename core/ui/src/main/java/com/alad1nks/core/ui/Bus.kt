package com.alad1nks.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alad1nks.core.model.Bus

@Composable
fun Bus.Content() {
    when(this) {
        is Bus.Upcoming -> {
            Column {
                ListItem(
                    modifier = Modifier
                        .padding(8.dp),
                    headlineContent = {
                        if (soon) {
                            Text(
                                text = timeLeft,
                                modifier = Modifier
                                    .background(
                                        color = Color.Red,
                                        shape = MaterialTheme.shapes.small
                                    )
                                    .padding(vertical = 8.dp, horizontal = 12.dp),
                                color = Color.White,
                                fontSize = 12.sp
                            )
                        } else {
                            Text(
                                text = timeLeft,
                                fontSize = 12.sp
                            )
                        }
                    },
                    leadingContent = station.timeContent(time),
                    trailingContent = station.nameContent()
                )
                HorizontalDivider()
            }
        }

        is Bus.Departed -> {
            Column {
                ListItem(
                    modifier = Modifier
                        .padding(8.dp),
                    headlineContent = {
                        Text(
                            text = timePassed,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            fontSize = 12.sp
                        )
                    },
                    leadingContent = {
                        Text(
                            text = time,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                            fontSize = 24.sp
                        )
                    },
                    trailingContent = station.nameContentDeparted()
                )
                HorizontalDivider()
            }
        }

        is Bus.Boyish -> {
            Column {
                ListItem(
                    modifier = Modifier
                        .padding(8.dp),
                    headlineContent = { },
                    leadingContent = station.timeContent(time),
                    trailingContent = station.nameContent()
                )
                HorizontalDivider()
            }
        }
    }
}