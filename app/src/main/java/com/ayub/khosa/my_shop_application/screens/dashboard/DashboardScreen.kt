package com.ayub.khosa.my_shop_application.screens.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.my_shop_application.R
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.screens.common.TitleText
import com.ayub.khosa.my_shop_application.screens.navigation.AppDestinations
import com.ayub.khosa.my_shop_application.utils.Constants
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Utils
import com.ayub.khosa.my_shop_application.utils.showToast

@SuppressLint("SuspiciousIndentation")
@Composable
fun DashboardScreen(navController: NavHostController) {
    val viewModel: DashboardViewModel = hiltViewModel()

    val context = LocalContext.current
    if (!Utils.isNetworkAvailable(context)) {
        showToast(context, "Network is not available")
    }

    val productsList: MutableState<List<Product>> = viewModel.productsList
    val categoriesList: MutableState<List<Category>> = viewModel.categoriesList



    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(1.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleText(
            Modifier.padding(top = 1.dp, start = 10.dp, end = 10.dp),

            "Home   Screen ",
            onTextClick = {
                PrintLogs.printInfo(" Go to SignIn Screen ")
                navController.navigate(AppDestinations.SignIn.screen_route) {
                    popUpTo(AppDestinations.SignIn.screen_route) {
                        inclusive = true
                    }
                    launchSingleTop = true
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
        if (Utils.isNetworkAvailable(context)) {

            if (categoriesList.value.isEmpty()) {

                CircularProgressIndicator(color = Color.Red)

            } else {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 1.dp)
                ) {
                    items(categoriesList.value) { category ->
                        MyCategoryCard(category, onClick = { category_clicked ->
                            viewModel.getProductsListByCategoryNameFromApi(category_clicked.name)

                        })
                    }
                }

            }
            if (productsList.value.isEmpty()) {

                CircularProgressIndicator(color = Color.Blue)

            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.LightGray),
                    contentPadding = PaddingValues(horizontal = 1.dp, vertical = 5.dp)
                ) {
                    items(productsList.value) { product ->
                        MyProductCard(product, onClick = { product_clicked ->
                            PrintLogs.printInfo(" Go to product Detail Screen ")

                            navController.navigate(AppDestinations.ProductDetail.screen_route + "/${product_clicked.id}") {
                                popUpTo(AppDestinations.ProductDetail.screen_route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        })
                    }
                }
            }
        }

    }
}


