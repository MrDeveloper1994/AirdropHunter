package com.scienpards.airdrophunter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random


@Composable
fun MainContent(innerPadding: PaddingValues) {
    var color by remember { mutableStateOf(Color.Blue) }
    var animationDuration by remember { mutableStateOf(1000L) }

    LaunchedEffect(Unit) {
        while (true) {

            animationDuration = Random.nextLong(3000, 6000)


            color = Color(
                red = Random.nextFloat(),
                green = Random.nextFloat(),
                blue = Random.nextFloat(),
                alpha = 1f
            )

            delay(animationDuration)
        }
    }






    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = """
           _         _                   _    _             _            
     /\   (_)       | |                 | |  | |           | |           
    /  \   _ _ __ __| |_ __ ___  _ __   | |__| |_   _ _ __ | |_ ___ _ __ 
   / /\ \ | | '__/ _` | '__/ _ \| '_ \  |  __  | | | | '_ \| __/ _ \ '__|
  / ____ \| | | | (_| | | | (_) | |_) | | |  | | |_| | | | | ||  __/ |   
 /_/    \_\_|_|  \__,_|_|  \___/| .__/  |_|  |_|\__,_|_| |_|\__\___|_|   
                                | |                                      
                                |_|                                      
""".trimIndent(),
            color = color,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 8.sp,
                fontFamily = FontFamily.Monospace,
                lineHeight = 14.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("متن اول", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(15.dp))
            Text("متن دوم", style = MaterialTheme.typography.titleSmall)
        }
    }
}

