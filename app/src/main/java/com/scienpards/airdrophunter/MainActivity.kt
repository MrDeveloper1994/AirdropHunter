package com.scienpards.airdrophunter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.scienpards.airdrophunter.ui.theme.AirdropHunterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AirdropHunterTheme(darkTheme = true) {
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

