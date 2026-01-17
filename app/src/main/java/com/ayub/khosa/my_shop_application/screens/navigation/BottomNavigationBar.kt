package com.ayub.khosa.my_shop_application.screens.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    val navItemList = listOf(
        BottomNavItem.HomeNav,
    )

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        NavigationBar {
            navItemList.forEach { screen ->
                NavigationBarItem(
                    selected = navBackStackEntry?.destination?.route == screen.route,
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    label = { Text(text = screen.title) },
                    alwaysShowLabel = false,
                    icon = {
                        Icon(
                            imageVector = if (navBackStackEntry?.destination?.route == screen.route)
                                screen.selectedIcon
                            else screen.unselectedIcon,
                            contentDescription = screen.title
                        )
                    }

                )
            }
        }
    }
}