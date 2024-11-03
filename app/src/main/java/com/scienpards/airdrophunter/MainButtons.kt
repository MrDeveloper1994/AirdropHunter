package com.scienpards.airdrophunter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ButtonColumn(coulmn_modifier: Modifier,button_modifier: Modifier) {
    val buttonColors = remember { mutableStateListOf(Color.Gray, Color.Gray, Color.Gray, Color.Gray, Color.Gray) }
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)

    Column(
        modifier = coulmn_modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { buttonColors[0] = colors.random() },
            colors = ButtonDefaults.buttonColors(containerColor  = buttonColors[0]),
            modifier = button_modifier
        ) {
            Text(text = "Button 1", color = Color.White)
        }

        Button(
            onClick = { buttonColors[1] = colors.random() },
            colors = ButtonDefaults.buttonColors(containerColor  = buttonColors[1]),
            modifier = button_modifier
        ) {
            Text(text = "Button 2", color = Color.White)
        }

        Button(
            onClick = { buttonColors[2] = colors.random() },
            colors = ButtonDefaults.buttonColors(containerColor  = buttonColors[2]),
            modifier = button_modifier
        ) {
            Text(text = "Button 3", color = Color.White)
        }

        Button(
            onClick = { buttonColors[3] = colors.random() },
            colors = ButtonDefaults.buttonColors(containerColor  = buttonColors[3]),
            modifier = button_modifier
        ) {
            Text(text = "Button 4", color = Color.White)
        }

        Button(
            onClick = { buttonColors[4] = colors.random() },
            colors = ButtonDefaults.buttonColors(containerColor  = buttonColors[4]),
            modifier = button_modifier
        ) {
            Text(text = "Button 5", color = Color.White)
        }
    }
}
