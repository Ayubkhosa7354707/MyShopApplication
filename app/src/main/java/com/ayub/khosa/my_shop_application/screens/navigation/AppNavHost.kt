package com.ayub.khosa.my_shop_application.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ayub.khosa.my_shop_application.screens.auth.signin.SignInScreen
import com.ayub.khosa.my_shop_application.screens.dashboard.DashboardScreen
import com.ayub.khosa.my_shop_application.screens.productdetail.ProductDetailScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    LocalContext.current
    NavHost(
        navController = navHostController,
        startDestination = AppDestinations.SignIn.fullRoute,
        modifier = modifier
    ) {
        composable(route = AppDestinations.SignIn.fullRoute) {
            SignInScreen(navHostController)
        }

        composable(route = AppDestinations.Home.fullRoute) {
            DashboardScreen(navHostController)
        }

        composable(route = AppDestinations.ProductDetail.fullRoute) { backStackEntry ->
            val product_id = backStackEntry.arguments?.getString("product_clicked.id") as String
            ProductDetailScreen(product_id, navHostController)
        }


    }
}