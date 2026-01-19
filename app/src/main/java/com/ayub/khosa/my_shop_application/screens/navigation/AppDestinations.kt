package com.ayub.khosa.my_shop_application.screens.navigation


sealed class AppDestinations(
    var screen_route: String,
    var arguments: String
) {


    object Home : AppDestinations("home", "") {
        val fullRoute = screen_route
    }

    object Profile : AppDestinations("profile", "") {
        val fullRoute = screen_route
    }


    object SignIn : AppDestinations("SignIn", "") {
        val fullRoute = screen_route + arguments
    }

    object ProductDetail : AppDestinations("detail", "/{product_clicked.id}") {
        val fullRoute = screen_route + arguments
    }

}