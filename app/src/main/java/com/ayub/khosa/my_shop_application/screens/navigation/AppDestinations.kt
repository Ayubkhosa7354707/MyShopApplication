package com.ayub.khosa.my_shop_application.screens.navigation



import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestinations {
    val route: String
}
object Home : AppDestinations {
    override val route = "home"
}


object SignIn : AppDestinations {
    override val route = "signIn"
}


