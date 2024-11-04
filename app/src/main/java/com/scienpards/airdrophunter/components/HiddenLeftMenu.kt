package com.scienpards.airdrophunter.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun HiddenLeftMenu() {
    val isMenuVisible = remember { mutableStateOf(false) }
    val menuWidth = 200.dp
    val animatedOffset = animateDpAsState(
        targetValue = if (isMenuVisible.value) 0.dp else -menuWidth,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing), label = ""
    )
    val animatedButtonOffset = animateDpAsState(
        targetValue = if (isMenuVisible.value) 180.dp else (-30).dp,
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { isMenuVisible.value = !isMenuVisible.value },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(0.dp)
                .width(100.dp)
                .offset(x = animatedButtonOffset.value),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.secondary
            ),
        ) {
            Text(
                "منو",
                modifier = Modifier.padding(start = 15.dp),
                color = MaterialTheme.colorScheme.onSecondary
            )
            AnimatedContent(targetState = isMenuVisible.value, label = "") { isVisible ->
                if (isVisible) {
                    Icon(
                        Icons.Default.Close,
                        modifier = Modifier.size(30.dp).padding(start = 3.dp),
                        contentDescription = "Close menu"
                    )
                } else {
                    Icon(
                        Icons.Default.Menu,
                        modifier = Modifier.size(30.dp).padding(start = 3.dp),
                        contentDescription = "Open menu"
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(menuWidth)
                .offset(x = animatedOffset.value)
                .background(MaterialTheme.colorScheme.secondary, MaterialTheme.shapes.small)


        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("آیتم 1", color = MaterialTheme.colorScheme.onPrimary)
                Text("آیتم 2", color = MaterialTheme.colorScheme.onPrimary)
                Text("آیتم 3", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
