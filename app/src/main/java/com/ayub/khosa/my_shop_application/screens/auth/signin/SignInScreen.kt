package com.ayub.khosa.my_shop_application.screens.auth.signin

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ayub.khosa.my_shop_application.R
import com.ayub.khosa.my_shop_application.screens.auth.AuthViewModel
import com.ayub.khosa.my_shop_application.screens.auth.signin.google.AuthenticationButton
import com.ayub.khosa.my_shop_application.screens.common.TitleText
import com.ayub.khosa.my_shop_application.screens.navigation.AppDestinations
import com.ayub.khosa.my_shop_application.ui.theme.MyShopApplicationTheme
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Utils
import com.ayub.khosa.my_shop_application.utils.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {


    //Check User Authenticated
    val isUserAuthenticated = viewModel.isUserSignInState.value


    val authState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    if (!Utils.isNetworkAvailable(context)) {
        showToast(context, "Network is not available")
    }

    if (isUserAuthenticated) {
        showToast(context, "SignInWithGoogle Successful")
        navController.navigate(AppDestinations.Home.screen_route) {
            popUpTo(AppDestinations.Home.screen_route) {
                inclusive = true
            }
            launchSingleTop = true
        }
        PrintLogs.printInfo("SignInWithGoogle Successful")
        PrintLogs.printInfo(" Go to home Screen ")
    }


    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
                .padding(16.dp), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(
                Modifier.padding(top = 1.dp, start = 10.dp, end = 10.dp),
                "Sign In Screen Firebase"
            )

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Transparent)
            )

            if (authState.equals("Loading")) {
                CircularProgressIndicator(color = Color.Blue)
            } else {
                AuthenticationButton(buttonText = "sign_in_with_google") { credential ->
                    viewModel.onSignInWithGoogle(credential)
                }
            }


        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MyShopApplicationTheme {
        SignInScreen(rememberNavController())
    }
}