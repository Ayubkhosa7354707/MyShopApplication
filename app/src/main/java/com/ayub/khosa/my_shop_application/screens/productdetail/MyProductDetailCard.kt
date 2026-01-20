package com.ayub.khosa.my_shop_application.screens.productdetail

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.Constants


@Composable
fun MyProductDetailCard(
    product: Product,
    is_in_cart: Boolean,
    addToCart: (Product) -> Unit,
    removeToCart: (Product) -> Unit
) {


    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 0.dp, top = 2.dp, end = 0.dp, bottom = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set the background color
            contentColor = Color.Black // Set the color for content (text/icons) inside the card
        )
    ) {
        Box(
            modifier = Modifier.wrapContentWidth(), // The Box fills the entire Card area
            contentAlignment = Alignment.Center // Centers the content inside the Box
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center, // Centers children vertically in the main axis
                horizontalAlignment = Alignment.CenterHorizontally // Centers children horizontally in the cross axis
            ) {
                Text(
                    text = "Title : " + product.name, textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    color = Color.Blue
                )


                AsyncImage(
                    model = Constants.BASE_URL + product.img,
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                )

                Text(
                    text = "Description : " + product.description,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(2.dp),
                    color = Color.Black
                )


                Text(
                    text = "Price : " + product.price + " PKR",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(2.dp),
                    color = Color.Red
                )

                if (is_in_cart == false) {
                    Button(
                        onClick = {
                            addToCart(product)
                        },
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        Text(text = "Add to Cart", color = Color.Blue)
                    }
                } else {
                    Button(
                        onClick = {
                            removeToCart(product)
                        },
                        modifier = Modifier.wrapContentSize(),
                    ) {
                        Text(text = "Remove to Cart", color = Color.Red)
                    }
                }

            }


        }


    }

}