package com.ayub.khosa.my_shop_application.screens.profilescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ayub.khosa.my_shop_application.domain.model.User


@Composable
fun MyUserView(user: User) {
    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center, // Centers children vertically in the main axis
        horizontalAlignment = Alignment.CenterHorizontally // Centers children horizontally in the cross axis

    ) {
        Image(
            painter = rememberAsyncImagePainter(user.photoUrl),
            contentDescription = "",
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(2.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)


        )
        Text(
            text = "Name : " + user.displayName,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier.padding(4.dp)
        )
        Text(
            text = "Email : " + user.email,
            fontWeight = FontWeight.Bold,
            color = Color.Blue,
            modifier = Modifier.padding(4.dp)
        )

    }
}
