package com.ayub.khosa.my_shop_application.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ayub.khosa.my_shop_application.R
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.screens.common.TitleText
import com.ayub.khosa.my_shop_application.screens.navigation.SignIn
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import com.ayub.khosa.my_shop_application.utils.Utils
import com.ayub.khosa.my_shop_application.utils.showToast

@Composable
fun DashboardScreen(navController: NavHostController) {
  val  viewModel: DashboardViewModel = hiltViewModel()

    val context = LocalContext.current
    if (!Utils.isNetworkAvailable(context)) {
        showToast(context, "Network is not available")
    }
    val productState by viewModel.products.collectAsState()
    val categoryState by viewModel.categories.collectAsState()


    //  showToast(context, "Resource.Success Not loged in ")
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

                "Home   Screen ",
                onTextClick = {
                    PrintLogs.printInfo(" Go to SignIn Screen ")
                    navController.navigate(SignIn.route){
                        popUpTo(SignIn.route) {
                            inclusive = true
                        }
                    }
                }
            )








            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Transparent)
            )


            when {
                productState is Response.Success  -> {
                    (productState as Response.Success<List<Product>>).data.forEach { product ->
                        PrintLogs.printInfo("product -> "+product.toString())
                    }
                }

                categoryState is Response.Success  -> {
                    (categoryState as Response.Success<List<Category>>).data.forEach { category ->
                        PrintLogs.printInfo("category -> "+category.toString())
                    }

                }

                productState is Response.Error   -> {
                    PrintLogs.printE(message = "productState Error")
                }


                categoryState is Response.Error -> {
                    PrintLogs.printE(message = "categoryState Error")
                }


            }









        }
    }

}