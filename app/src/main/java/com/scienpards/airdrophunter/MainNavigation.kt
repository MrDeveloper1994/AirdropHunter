package com.scienpards.airdrophunter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.scienpards.airdrophunter.dataManager.UserModel
import com.scienpards.airdrophunter.screen.MainScreen
import com.scienpards.airdrophunter.screen.SignUpUserScreen
import com.scienpards.airdrophunter.screen.UserFineScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    val userModel: UserModel = hiltViewModel(   )
    NavHost(navController = navController, startDestination = "home", modifier =modifier ) {

        composable("home") { MainScreen(navController) }
//        composable("home") { TestScreen(Modifier.Companion.padding(innerPadding)) }
        composable("signup") { SignUpUserScreen(navController,userModel) }
        composable("userfind") { UserFineScreen(navController,userModel) }
    }
}
