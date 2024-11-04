package com.scienpards.airdrophunter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.scienpards.airdrophunter.ui.theme.AirdropHunterTheme
import com.scienpards.airdrophunter.ui.theme.LightSecondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        WindowCompat.setDecorFitsSystemWindows(window, false)

        enableEdgeToEdge()
        window.navigationBarColor = LightSecondary.toArgb()
        window.navigationBarDividerColor = LightSecondary.toArgb()
        window.statusBarColor = LightSecondary.toArgb()

        setContent {
            AirdropHunterTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                val navController = rememberNavController()
                MainNavigation(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    }}
}

