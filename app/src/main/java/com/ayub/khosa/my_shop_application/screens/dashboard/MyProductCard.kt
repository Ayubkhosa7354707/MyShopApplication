package com.ayub.khosa.my_shop_application.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.Constants


@Composable
fun MyProductCard(product: Product, onClick: (Product) -> Unit) {

    val viewModel: DashboardViewModel = hiltViewModel()
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(product) }
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ) ,
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set the background color
            contentColor = Color.Black // Set the color for content (text/icons) inside the card
        )
    ) {
        Text(
            text = "" + product.name, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Blue
        )
        Box(
            modifier = Modifier.fillMaxSize(), // The Box fills the entire Card area
            contentAlignment = Alignment.Center // Centers the content inside the Box
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .border(1.dp, Color.Black),
                verticalAlignment = Alignment.CenterVertically,


                ) {
                Image(
                    painter = rememberAsyncImagePainter(Constants.BASE_URL + product.img),

                    contentDescription = "" + product.description,
                    modifier = Modifier
                        .height(120.dp)
                        .width(150.dp)
                        .padding(2.dp)
                        .border(2.dp, Color.Black)
                        .clip(RoundedCornerShape(20.dp))

                )
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                ) {


                    Text(
                        text = "Price :" + product.price + " PKR",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(2.dp),
                        color = Color.Red
                    )
                    Text(
                        text = "Category : " + product.category,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(2.dp),
                        color = Color.Black
                    )
                    Button(
                        onClick = {
                            var userCart: UserCart = UserCart(
                                productId = product.id,
                                price = product.price,
                                quantity = 2,
                                title = product.name,
                                image = Constants.BASE_URL + product.img,
                                userId = "",
                            )

                            viewModel.addToCart(userCart)
                        },
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        Text(text = "Add to Cart")
                    }
                }


            }

        }
    }

}
