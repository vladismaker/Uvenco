package com.application.uvencotest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.application.uvencotest.viewmodels.MainViewModel
import com.application.uvencotest.ui.theme.compose_files.DetailScreen
import com.application.uvencotest.ui.theme.compose_files.MainScreen

sealed class NavRoute(val route: String) {
    data object Main : NavRoute("main_screen")
    data object Detail : NavRoute("detail_screen")
}

@Composable
fun AppNavHost(navController: NavHostController,
               mainViewModel: MainViewModel
) {

    NavHost(
        navController = navController, startDestination = NavRoute.Main.route
    ) {
        composable(NavRoute.Main.route){MainScreen(navController, mainViewModel)}
        composable(NavRoute.Detail.route){DetailScreen(navController, mainViewModel)}
    }
}