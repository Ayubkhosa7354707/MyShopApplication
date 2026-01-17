package com.ayub.khosa.my_shop_application.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ayub.khosa.my_shop_application.screens.navigation.AppNavHost
import com.ayub.khosa.my_shop_application.screens.navigation.BottomNavigationBar
import com.ayub.khosa.my_shop_application.screens.navigation.SignIn

@Composable
 fun MainScreen (){


    val navHostController = rememberNavController()
    val bottomBarState = rememberSaveable { mutableStateOf(false) }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        SignIn.route,    -> bottomBarState.value =
            false

        else -> bottomBarState.value = true
    }

    Scaffold(
        bottomBar = {
            if (bottomBarState.value) {
                BottomNavigationBar(
                    navController = navHostController,
                    bottomBarState = bottomBarState,
                )
            }
        }
    ) { paddingValues ->
        AppNavHost(
            navHostController = navHostController,
            modifier = Modifier.padding(paddingValues)
        )

    }

}