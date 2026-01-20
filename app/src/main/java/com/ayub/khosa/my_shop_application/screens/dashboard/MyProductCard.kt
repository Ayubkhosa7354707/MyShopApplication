package com.ayub.khosa.my_shop_application.screens.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.Constants


@Composable
fun MyProductCard(product: Product, onClick: (Product) -> Unit) {

    val viewModel: DashboardViewModel = hiltViewModel()
    Card(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick(product) }
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
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
            modifier = Modifier.wrapContentSize(), // The Box fills the entire Card area
            contentAlignment = Alignment.Center // Centers the content inside the Box
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically,


                ) {

                AsyncImage(
                    model = Constants.BASE_URL + product.img,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))
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
                                quantity = 1,
                                title = product.name,
                                image = Constants.BASE_URL + product.img,
                                userId = "",
                            )

                            viewModel.addToCart(userCart)
                        },
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        Text(text = "Add to Cart", color = Color.Blue)
                    }


                }


            }

        }
    }

}
