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
import androidx.compose.material3.ButtonDefaults
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
import coil.compose.AsyncImage
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.Constants
import com.ayub.khosa.my_shop_application.utils.PrintLogs


@Composable
fun MyProductCard(
    product: Product,
    is_in_cart: Boolean,
    onClick: (Product) -> Unit,
    addToCart: (Product) -> Unit,
    removeToCart: (Product) -> Unit
) {

//    val viewModel: DashboardViewModel = hiltViewModel()

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

                    PrintLogs.printInfo("My Product Card Screen ")


                    PrintLogs.printInfo("My Product Card Screen" + product.id + "  " + is_in_cart.toString())
                    if (is_in_cart) {
                        Button(
                            onClick = {
                                removeToCart(product)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,           // Sets the button's background color
                                contentColor = Color.White,           // Sets the text/icon color
                                disabledContainerColor = Color.Gray,  // Color when the button is disabled
                                disabledContentColor = Color.DarkGray // Content color when disabled
                            ),
                            modifier = Modifier.wrapContentSize(),
                        ) {
                            Text(text = "Remove to Cart", color = Color.Red)
                        }
                    } else {
                        Button(
                            onClick = {
                                addToCart(product)
                            },
                            modifier = Modifier.wrapContentSize(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,           // Sets the button's background color
                                contentColor = Color.White,           // Sets the text/icon color
                                disabledContainerColor = Color.Gray,  // Color when the button is disabled
                                disabledContentColor = Color.DarkGray // Content color when disabled
                            )
                        ) {
                            Text(text = "Add to Cart", color = Color.White)
                        }
                    }


                }
            }
        }
    }
}
