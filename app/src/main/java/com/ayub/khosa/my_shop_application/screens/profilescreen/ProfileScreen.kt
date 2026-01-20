package com.ayub.khosa.my_shop_application.screens.profilescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ayub.khosa.my_shop_application.screens.common.TitleText
import com.ayub.khosa.my_shop_application.screens.navigation.AppDestinations
import com.ayub.khosa.my_shop_application.utils.PrintLogs

@Composable
fun ProfileScreen(navController: NavHostController) {
    val viewModel: ProfileViewModel = hiltViewModel()

    val user = viewModel.user.collectAsState()

    val authState by viewModel.uiState.collectAsState()

    if (authState.equals("Success")) {
        // go to signin screen
        navController.navigate(AppDestinations.SignIn.screen_route) {
            popUpTo(AppDestinations.SignIn.screen_route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }



    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(1.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(
            Modifier.padding(top = 1.dp, start = 10.dp, end = 10.dp),
            "Profile Screen  ",
            onTextClick = {
                PrintLogs.printInfo(" Go to Dashboard Screen ")
                navController.navigate(AppDestinations.Home.screen_route) {
                    popUpTo(AppDestinations.Home.screen_route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )




        if (user.value.uid + "" != "0") {
            MyUserView(user.value)
        }
        Button(
            onClick = {
                viewModel.signOut()
            },
            modifier = Modifier.wrapContentSize(),
            shape = RectangleShape,
        ) {
            Text(text = "Sign Out")
        }

    }
}
