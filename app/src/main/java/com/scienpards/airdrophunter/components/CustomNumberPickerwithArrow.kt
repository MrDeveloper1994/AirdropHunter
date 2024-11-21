package com.scienpards.airdrophunter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomNumberPickerWithArrow(onNumberChanged:(Int)->Unit) {
    var number by remember { mutableStateOf(1) }

    Row(
//        modifier = Modifier.height(),
//        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
//        modifier = Modifier.width()
    ) {

        IconButton(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primary).height(45.dp).shadow(16.dp, ambientColor = Color.Black),
            onClick = {
                if (number > 0) {
                    number--
                    onNumberChanged(number)
                }
            }
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Decrease")
        }


        TextField(
            value = number.toString(),
            onValueChange = { input ->

                val validNumbers = listOf("0", "1", "2")
                if (input in validNumbers) {
                    number = input.toInt()
                    onNumberChanged(number)
                }
            },
            label = {
                Text(
                    "لاگ چند روز گذشته",
                    style = TextStyle(fontSize = 12.sp)
                )
            },
            textStyle = TextStyle(fontSize = 14.sp), singleLine = true,
            modifier = Modifier.width(150.dp).padding(0.dp).height(45.dp).shadow(16.dp, ambientColor = Color.Black),

        )


        IconButton(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primary).height(45.dp).shadow(16.dp, ambientColor = Color.Black),
            onClick = {
                if (number < 2) {
                    number++
                    onNumberChanged(number)
                }
            }
        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Increase")
        }
    }
}


