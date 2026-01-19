package com.ayub.khosa.my_shop_application.screens.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.my_shop_application.domain.model.User
import com.ayub.khosa.my_shop_application.screens.common.TitleText
import com.ayub.khosa.my_shop_application.screens.navigation.AppDestinations
import com.ayub.khosa.my_shop_application.utils.PrintLogs

@Composable
fun  ProfileScreen( navController: NavHostController)  {
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




        if(user.value.uid+""!="0"){
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

@Composable
fun MyUserView(user: User) {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center, // Centers children vertically in the main axis
                horizontalAlignment = Alignment.CenterHorizontally // Centers children horizontally in the cross axis

            ) {
                Image(
                    painter = rememberAsyncImagePainter(user.photoUrl),
                    contentDescription = "",
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(2.dp)
                        .border(2.dp, Color.Black)
                        .clip(RoundedCornerShape(20.dp))

                )
                Text(
                    text = "Name : " + user.displayName,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Email : " + user.email,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier.padding(4.dp)
                )

    }
}
