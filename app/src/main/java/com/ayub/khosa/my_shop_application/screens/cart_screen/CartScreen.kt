package com.ayub.khosa.my_shop_application.screens.cart_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.screens.common.TitleText
import com.ayub.khosa.my_shop_application.screens.navigation.AppDestinations
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import com.ayub.khosa.my_shop_application.utils.showToast

@Composable
fun CartScreen (navController: NavHostController, viewModel: CartViewModel = hiltViewModel(),){


    val context = LocalContext.current
    val cartState by viewModel.userCart.collectAsState(initial = Response.Idle)
    val onCartLongClicked = { userCart: UserCart ->
        viewModel.deleteUserCartItem(userCart = userCart)
        showToast(  context,"Item Deleted")
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
            "Cart Screen  ",
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

        when (cartState) {
            is Response.Error -> {
                PrintLogs.printE( " cartState Error ")
            }
            Response.Idle -> {
                PrintLogs.printE( " cartState Idle ")
            }
            Response.Loading -> {
                PrintLogs.printInfo( " cartState Loading ")
            }
            is Response.Success<*> -> {
                PrintLogs.printInfo( " cartState Success ")
              val  userCartList = (cartState as Response.Success).data

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(userCartList.size) { cart ->
                        CartItem(
                            userCart = userCartList[cart],
                            onCartLongClicked = onCartLongClicked,
                            onCartItemClicked = {
                                PrintLogs.printInfo(" Cart  Screen "+it.title+" Clicked ")
                            } ,
                        )
                    }
                }

                }
            }
        }


    }




