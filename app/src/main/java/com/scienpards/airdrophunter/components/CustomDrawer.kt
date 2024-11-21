package com.scienpards.airdrophunter.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.rememberDrawerState
import androidx.compose.ui.graphics.RectangleShape
import com.scienpards.airdrophunter.models.LogFilter
import kotlinx.coroutines.launch


@Composable
fun CustomDrawer(logFilter: LogFilter) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = {
                        logFilter.endpoint =
                            logFilter.copy(endpoint = "Goats").toString()
                    }
                )
                // ...other drawer items
            }
        }) {
        Button(
            onClick = {
                scope.launch {
                    drawerState.apply {
                        if (drawerState.isClosed) drawerState.open() else drawerState.close()
                    }
                }
            }, modifier = Modifier
                .width(100.dp), shape = RectangleShape, contentPadding = PaddingValues(0.dp)
        ) {
            Text("Filter Endpoint", color = Color.White)
        }
    }
}
