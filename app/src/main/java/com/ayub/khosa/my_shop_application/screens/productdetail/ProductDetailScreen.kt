package com.ayub.khosa.my_shop_application.screens.productdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.screens.common.TitleText
import com.ayub.khosa.my_shop_application.screens.navigation.AppDestinations
import com.ayub.khosa.my_shop_application.utils.Constants
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Utils
import com.ayub.khosa.my_shop_application.utils.showToast

@Composable
fun ProductDetailScreen(product_id: String, navController: NavHostController) {

    val viewModel: ProductDetailViewModel = hiltViewModel()

    val product: MutableState<Product> = viewModel.product
    if ("" + product.value.id != product_id) {
        viewModel.getSingleProductByIdFromApi(product_id)
    }
    PrintLogs.printInfo("ProductDetailScreen")

    val context = LocalContext.current
    if (!Utils.isNetworkAvailable(context)) {
        showToast(context, "Network is not available")
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

            "Product Detail Screen  ",
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
        if (Utils.isNetworkAvailable(context)) {
            Button(
                onClick = {
                    var userCart: UserCart = UserCart(
                        productId = product.value.id,
                        price = product.value.price,
                        quantity = 1,
                        title = product.value.name,
                        image = Constants.BASE_URL + product.value.img,
                        userId = "",
                    )
                    viewModel.addToCart(userCart)
                },
                modifier = Modifier.wrapContentSize(),
                shape = RectangleShape,
            ) {
                Text(text = "Add to Cart")
            }
            MyProductDetailCard(product.value)


        }

    }
}

