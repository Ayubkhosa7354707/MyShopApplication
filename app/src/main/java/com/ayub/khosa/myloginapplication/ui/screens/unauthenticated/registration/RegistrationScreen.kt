package com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayub.khosa.myloginapplication.common.TitleText
import com.ayub.khosa.myloginapplication.common.CustomDefaultBtn

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel, email: String,
    onNavigateBack: () -> Boolean
) {


    val textValue by viewModel.textValue.collectAsState()

    var input_email by rememberSaveable { mutableStateOf("") }
    var input_password by rememberSaveable { mutableStateOf("") }
    var input_first_name by rememberSaveable { mutableStateOf("") }
    var input_last_name by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }


    TitleText(Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp), "Registration Screen")
    Column(modifier = Modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {


        OutlinedTextField(
            value = input_email, singleLine = true,
            onValueChange = { email -> input_email = email },
            label = { Text("Enter your email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (isPasswordVisible) {

                VisualTransformation.None

            } else {

                PasswordVisualTransformation()

            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = input_password,
            onValueChange = { newText ->
                input_password = newText
            },
            label = {
                Text(text = "Password")
            },
            trailingIcon = {
                if (isPasswordVisible) {
                    IconButton(onClick = { isPasswordVisible = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { isPasswordVisible = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            placeholder = { Text(text = "Type password here") },
            shape = RoundedCornerShape(percent = 0),
        )

        OutlinedTextField(
            value = input_first_name, singleLine = true,
            onValueChange = { newText -> input_first_name = newText },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = input_last_name, singleLine = true,
            onValueChange = { newText -> input_last_name = newText },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomDefaultBtn(
                btnText = "Sign Up Button",
                onClick = {
                    viewModel.RegistrationViewModel_user_signup_Clicked(
                        input_email,
                        input_password,
                        input_first_name,
                        input_last_name
                    )
                },
            )
            Text(
                modifier = Modifier
                    .clickable {
                        onNavigateBack.invoke()
                    }
                    .padding(10.dp)
                    .fillMaxWidth(),
                text = "Back to login ",
                color = Color.Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center

            )
        }
        Text(
            text = "" + textValue,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(
        viewModel = viewModel(),
        email = ("PayU"),
        onNavigateBack = { true },
    )
}