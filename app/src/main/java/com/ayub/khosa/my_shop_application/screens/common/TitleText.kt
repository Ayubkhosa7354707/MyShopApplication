package com.ayub.khosa.my_shop_application.screens.common


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    text: String,

    textAlign: TextAlign = TextAlign.Center,
    onTextClick: () -> Unit = {}
) {

                TextButton(onClick = {
                        onTextClick()
            }) {
                    Text(
                        modifier = modifier,
                        text = text,
                        textAlign = textAlign,
                        fontFamily = FontFamily.Serif,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.secondary
                    )
            }

}