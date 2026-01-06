package com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.forgotpassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayub.khosa.myloginapplication.common.TitleText
import com.ayub.khosa.myloginapplication.common.CustomDefaultBtn


@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = viewModel(),
    email: String,
    onNavigateBack: () -> Boolean,
    modifier: Modifier = Modifier
) {
    val textValue by viewModel.textValue.collectAsState()

    var input_email by rememberSaveable { mutableStateOf("") }

    TitleText(Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp), "Forgot Password Screen")
    Column(modifier = modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {

        Column(modifier = modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp)) {


            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = input_email, singleLine = true,
                onValueChange = { email -> input_email = email },
                label = { Text("Enter your email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomDefaultBtn(
                    btnText = "Generate Password ",
                    onClick = {
                        viewModel.forgotPasswordViewModel_user_forgetpassword_Clicked(input_email)
                    },
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            onNavigateBack.invoke()
                        }
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = "login ",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center

                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = " " + textValue,
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(
        viewModel = viewModel(),
        email = ("PayU"),
        onNavigateBack = { true },
    )
}