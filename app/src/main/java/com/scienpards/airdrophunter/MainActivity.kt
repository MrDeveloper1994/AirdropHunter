package com.scienpards.airdrophunter
import androidx.compose.ui.Alignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.scienpards.airdrophunter.ui.theme.AirdropHunterTheme
import com.scienpards.airdrophunter.ui.theme.LightPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        window.navigationBarColor = LightPrimary.toArgb()
        window.navigationBarDividerColor = LightPrimary.toArgb()
        window.statusBarColor = LightPrimary.toArgb()
        setContent {
            AirdropHunterTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(Modifier.padding(innerPadding))
                    MainContent(innerPadding)
//                    GradientText()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "",
        modifier = modifier
    )
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AirdropHunterTheme {
        Column {

            Greeting()
        }
    }
}

