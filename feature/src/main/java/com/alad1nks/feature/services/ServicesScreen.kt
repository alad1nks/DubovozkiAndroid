package com.alad1nks.feature.services

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.outlined.CurrencyRuble
import androidx.compose.material.icons.outlined.LocalLaundryService
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alad1nks.core.model.NavigationItem

@Composable
fun ServicesScreen(
    navController: NavHostController
) {
    val uriHandler = LocalUriHandler.current
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 16.dp)
        ) {
            item {
                Text(
                    text = "Сервисы",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                ServiceItem(
                    title = "Расписание кастелянной",
                    imageVector = Icons.Outlined.LocalLaundryService,
                    onClick = {
                        navController.navigate(NavigationItem.CastellanScreen.route)
                    }
                )
                ServiceItem(
                    title = "Поддержать автора",
                    imageVector = Icons.Outlined.CurrencyRuble,
                    onClick = {
                        uriHandler.openUri("https://www.tinkoff.ru/cf/83xxJOIMkCv")
                    }
                )
                ServiceItem(
                    title = "Связаться с разработчиком",
                    imageVector = Icons.AutoMirrored.Outlined.Chat,
                    onClick = {
                        uriHandler.openUri("https://t.me/asteslenko")
                    }
                )
            }
        }
    }
}

@Composable
private fun ServiceItem(
    title: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        ListItem(
            headlineContent = {
                Text(
                    text = title
                )
                              },
            trailingContent = {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
