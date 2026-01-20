package com.ayub.khosa.my_shop_application.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ayub.khosa.my_shop_application.screens.auth.signin.SignInScreen
import com.ayub.khosa.my_shop_application.screens.cart_screen.CartScreen
import com.ayub.khosa.my_shop_application.screens.dashboard.DashboardScreen
import com.ayub.khosa.my_shop_application.screens.productdetail.ProductDetailScreen
import com.ayub.khosa.my_shop_application.screens.profilescreen.ProfileScreen
import com.ayub.khosa.my_shop_application.screens.profilescreen.ProfileViewModel
import com.ayub.khosa.my_shop_application.utils.PrintLogs

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val viewModel: ProfileViewModel = hiltViewModel()


    val start = if (viewModel.user.collectAsState().value.uid.toString() != "") {
        PrintLogs.printInfo("AppNavHost   Home Screen" + viewModel.user.collectAsState().value.uid)
        AppDestinations.Home.fullRoute
    } else {
        PrintLogs.printInfo("AppNavHostGo to Signin Screen" + viewModel.user.collectAsState().value.uid)
        AppDestinations.SignIn.fullRoute
    }

    NavHost(
        navController = navHostController,
        startDestination = start,
        modifier = modifier
    ) {
        composable(route = AppDestinations.SignIn.fullRoute) {
            SignInScreen(navHostController)
        }

        composable(route = AppDestinations.Home.fullRoute) {
            DashboardScreen(navHostController)
        }
        composable(route = AppDestinations.Profile.fullRoute) {
            ProfileScreen(navHostController)
        }

        composable(route = AppDestinations.Cart.fullRoute) {
            CartScreen(navHostController)
        }

        composable(route = AppDestinations.ProductDetail.fullRoute) { backStackEntry ->
            val product_id = backStackEntry.arguments?.getString("product_clicked.id") as String
            ProductDetailScreen(product_id, navHostController)
        }


    }
}