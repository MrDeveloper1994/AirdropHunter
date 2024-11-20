package com.scienpards.airdrophunter

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.scienpards.airdrophunter.screen.CircleScreen
import com.scienpards.airdrophunter.screen.GoatsScreen
import com.scienpards.airdrophunter.viewModel.UserViewModel
import com.scienpards.airdrophunter.screen.MainScreen
import com.scienpards.airdrophunter.screen.SignUpUserScreen
import com.scienpards.airdrophunter.screen.UserFineScreen
import com.scienpards.airdrophunter.viewModel.LogViewModel
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    val userViewModel: UserViewModel = hiltViewModel(   )
    val logViewModel: LogViewModel = hiltViewModel(   )
    /* NavHost(navController = navController, startDestination = "home", modifier =modifier ) {

        composable("home") { MainScreen(navController) }
       composable("signup") { SignUpUserScreen(navController,userViewModel) }
      composable("userManager") { UserFineScreen(navController,userViewModel) }
       composable("miniApp") { GoatsScreen(logViewModel) }
       composable("circle") { CircleScreen(logViewModel) } } */



    AnimatedNavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier,
//        enterTransition = { defaultEnterTransition() },
//        exitTransition = { defaultExitTransition() },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() },
        enterTransition = { flipEnterTransition() },
        exitTransition = { flipExitTransition() },
//        popEnterTransition = { flipPopEnterTransition() },
//        popExitTransition = { flipPopExitTransition() }
    ) {
        composable("home") { MainScreen(navController) }
        composable("signup") { SignUpUserScreen(navController, userViewModel) }
        composable("userManager") { UserFineScreen(navController, userViewModel) }
        composable("miniApp") { GoatsScreen(logViewModel) }
//        composable("circle") { CircleScreen(logViewModel) }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun defaultEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth }, // ورود از راست
        animationSpec = tween(durationMillis = 1000)
    ) + fadeIn(animationSpec = tween(1000))
}

@OptIn(ExperimentalAnimationApi::class)
fun defaultExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { fullWidth -> -fullWidth }, // خروج به چپ
        animationSpec = tween(durationMillis = 1000)
    ) + fadeOut(animationSpec = tween(1000))
}

@OptIn(ExperimentalAnimationApi::class)
fun defaultPopEnterTransition(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth }, // ورود از چپ
        animationSpec = tween(durationMillis = 1000)
    ) + fadeIn(animationSpec = tween(1000))
}

@OptIn(ExperimentalAnimationApi::class)
fun defaultPopExitTransition(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { fullWidth -> fullWidth }, // خروج به راست
        animationSpec = tween(durationMillis = 1000)
    ) + fadeOut(animationSpec = tween(1000))
}

@OptIn(ExperimentalAnimationApi::class)
fun flipEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(1000)) + scaleIn(
        animationSpec = tween(1000),
        initialScale = 0.8f
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun flipExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(1000)) + scaleOut(
        animationSpec = tween(1000),
        targetScale = 1.2f
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun flipPopEnterTransition(): EnterTransition {
    return fadeIn(animationSpec = tween(1000)) + scaleIn(
        animationSpec = tween(1000),
        initialScale = 0.8f
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun flipPopExitTransition(): ExitTransition {
    return fadeOut(animationSpec = tween(1000)) + scaleOut(
        animationSpec = tween(1000),
        targetScale = 1.2f
    )
}



