package com.ark.tg.forwarder.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ark.tg.forwarder.presentation.features.auth.screen.PhoneScreen
import com.ark.tg.forwarder.presentation.features.forwardfrom.screen.ForwardFromScreen
import com.ark.tg.forwarder.presentation.features.forwardto.screen.ForwardToScreen
import com.ark.tg.forwarder.presentation.features.home.screen.HomeScreen


@Composable
fun RootNavHost(
    modifier: Modifier = Modifier,
    startDestination: Destinations
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { slideInHorizontally(tween(300)) { it } },
        exitTransition = { slideOutHorizontally(tween(300)) { -it } },
        popEnterTransition = { slideInHorizontally(tween(300)) { -it } },
        popExitTransition = { slideOutHorizontally(tween(300)) { it } },
        modifier = modifier.fillMaxSize()
    ) {

        composable<Destinations.Phone> {
            PhoneScreen()
        }

        composable<Destinations.Home> {
            HomeScreen(
                onFabClicked = {
                    navController.navigate(Destinations.ForwardFrom)
                },
            )
        }

        composable<Destinations.ForwardFrom> {
            ForwardFromScreen(
                onBackClicked = { navController.popBackStack() },
                onNextClicked = {
                    navController.navigate(Destinations.ForwardTo)
                }
            )
        }

        composable<Destinations.ForwardTo> {
            ForwardToScreen(
                onBackClicked = { navController.popBackStack() },
                onSubmitClicked = {
                    navController.popBackStack(Destinations.Home, inclusive = false)
                }
            )
        }
    }
}