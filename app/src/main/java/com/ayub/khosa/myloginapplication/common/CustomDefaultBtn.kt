package com.ayub.khosa.myloginapplication.common


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CustomDefaultBtn(
    btnText: String,
    onClick: () -> Unit
) {

    Button(
        shape = RectangleShape,
        onClick = {
            onClick()
        },
    ) {
        Text(text = btnText, fontSize = 16.sp)
    }


}

@Preview(showBackground = true)
@Composable
fun CustomBtnPreview() {
    CustomDefaultBtn(btnText = "Save", {

    })
}