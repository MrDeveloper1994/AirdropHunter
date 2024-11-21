package com.scienpards.airdrophunter.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun CustomDrawerMenu(options:List<String>,selectOption:String, onOptionSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    val option = listOf("همه", "Goats", "Circle")
    var selectedOption by remember { mutableStateOf(" مینی اپ ها") }
    val heightDrawer by animateDpAsState(
        targetValue = if (expanded) 160.dp else 0.dp,
        animationSpec = tween(durationMillis = 400)
    )
    LaunchedEffect(selectOption) {
        selectedOption = "${(selectOption) ?: ""}"
    }

    Column(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .width(150.dp)
                .shadow(16.dp, ambientColor = MaterialTheme.colorScheme.primary),
            shape = RectangleShape,
//            contentPadding = PaddingValues(0.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    selectedOption,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                AnimatedContent(targetState = expanded, label = "label") { isVisible ->
                    if (isVisible) {
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            modifier = Modifier
                                .padding(start = 5.dp),
                            contentDescription = "Close menu"
                        )
                    } else {
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            modifier = Modifier
                                .padding(start = 5.dp),
                            contentDescription = "Open menu"
                        )
                    }
                }
            }
        }
        DropdownMenu(
            expanded = expanded,
            modifier = Modifier
                .height(heightDrawer)
                .width(150.dp)
                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)),
            onDismissRequest = { expanded = false }
        ) {
            if (heightDrawer > 0.dp) {
                Column {
                    options.forEach { option ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false
                                onOptionSelect(option)
                            }
                        )
                        HorizontalDivider(modifier = Modifier.height(1.dp))
                    }
                }
            }
        }
    }
}
