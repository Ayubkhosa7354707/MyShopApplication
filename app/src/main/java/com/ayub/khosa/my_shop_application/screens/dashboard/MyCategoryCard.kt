package com.ayub.khosa.my_shop_application.screens.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.utils.Constants


@Composable
fun MyCategoryCard(category: Category, onClick: (Category) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick(category) }
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White, // Set the background color
            contentColor = Color.Black // Set the color for content (text/icons) inside the card
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentSize()
        ) {
//
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.Center, // Centers children vertically in the main axis
                horizontalAlignment = Alignment.CenterHorizontally // Centers children horizontally in the cross axis

            ) {

                AsyncImage(
                    model = Constants.BASE_URL + category.img,
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .border(2.dp, Color.Black, RoundedCornerShape(15.dp))
                )
                Text(
                    text = "" + category.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red, fontSize = 16.sp,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}


