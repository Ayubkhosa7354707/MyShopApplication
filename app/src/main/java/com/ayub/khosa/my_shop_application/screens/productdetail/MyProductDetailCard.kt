package com.ayub.khosa.my_shop_application.screens.productdetail

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.Constants


@Composable
fun MyProductDetailCard(product: Product) {


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


                Image(
                    painter = rememberAsyncImagePainter(Constants.BASE_URL + product.img),

                    contentDescription = "" + product.description,
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                        .padding(2.dp)
                        .border(2.dp, Color.Black)
                        .clip(RoundedCornerShape(20.dp))

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
            }


        }


    }

}