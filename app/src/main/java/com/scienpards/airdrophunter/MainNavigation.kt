package com.scienpards.airdrophunter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.scienpards.airdrophunter.screen.MainScreen
import com.scienpards.airdrophunter.screen.SignupUserScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavigation(navController: NavHostController,modifier: Modifier) {
    NavHost(navController = navController, startDestination = "home", modifier =modifier ) {
        composable("home") { MainScreen(navController) }
        composable("signup") { SignupUserScreen() }
    }
}
