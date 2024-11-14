package com.scienpards.airdrophunter

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.scienpards.airdrophunter.ui.theme.AirdropHunterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {
            AirdropHunterTheme(darkTheme = false) {
                window.navigationBarColor = MaterialTheme.colorScheme.secondary.toArgb()
                window.navigationBarDividerColor = MaterialTheme.colorScheme.secondary.toArgb()
                window.statusBarColor = MaterialTheme.colorScheme.secondary.toArgb()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    MainNavigation(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)

                    )
//                    TestScreen(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

