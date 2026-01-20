package com.ayub.khosa.my_shop_application.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.utils.Constants


@Composable
fun MyCategoryCard(category: Category, onClick: (Category) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick(category) }
            .wrapContentWidth()
            .height(150.dp)
            .padding(15.dp),
            elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
//
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center, // Centers children vertically in the main axis
                horizontalAlignment = Alignment.CenterHorizontally // Centers children horizontally in the cross axis

            ) {
                Image(
                    painter = rememberAsyncImagePainter(Constants.BASE_URL + category.img),

                    contentDescription = "",
                    modifier = Modifier
                        .height(80.dp)
                        .width(100.dp)
                        .padding(2.dp)
                        .border(2.dp, Color.Black)
                        .clip(RoundedCornerShape(20.dp))

                )
                Text(
                    text = "" + category.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}


