package com.scienpards.airdrophunter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.scienpards.airdrophunter.screen.MainScreen
import com.scienpards.airdrophunter.screen.SignUpUserScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.scienpards.airdrophunter.dataManager.UserModel

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier,
) {

    NavHost(navController = navController, startDestination = "home", modifier =modifier ) {
        val  userModel = UserModel()
        composable("home") { MainScreen(navController) }
        composable("signup") { SignUpUserScreen(navController, userModel) }
    }
}
