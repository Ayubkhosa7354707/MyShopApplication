package com.ayub.khosa.my_shop_application.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ayub.khosa.my_shop_application.screens.auth.signin.SignInScreen
import com.ayub.khosa.my_shop_application.screens.dashboard.DashboardScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    NavHost(
        navController = navHostController,
        startDestination = SignIn.route,
        modifier = modifier
    ) {
        composable(route = SignIn.route) {
            SignInScreen(navHostController)
        }



        composable(route = Home.route) {

            DashboardScreen( navHostController )
        }



    }
}