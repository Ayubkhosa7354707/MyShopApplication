package com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.login

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
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayub.khosa.myloginapplication.common.TitleText
import com.ayub.khosa.myloginapplication.common.CustomDefaultBtn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(), onNavigateToRegistration: (email: String) -> Unit,
    onNavigateToForgotPassword: (email: String) -> Unit,
    onNavigateToAuthenticatedRoute: (email: String) -> Unit, modifier: Modifier = Modifier
) {


    val textValue by viewModel.textValue.collectAsState()
    val toAuthenticatedRoute by viewModel.toAuthenticatedRoute.collectAsState()

//    LoginViewModel  email -> ayub.khosa@gmail.com
//    LoginViewModel password ->ayub
    var input_email by rememberSaveable { mutableStateOf("") }
    var input_password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }
    TitleText(Modifier.padding(top = 30.dp, start = 10.dp, end = 10.dp), "Login Screen")
    Column(modifier = modifier.padding(top = 80.dp, start = 10.dp, end = 10.dp)) {

        OutlinedTextField(
            value = input_email, singleLine = true,
            onValueChange = { newText -> input_email = newText },
            label = { Text("Enter ayub.khosa@gmail.com") },
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
                Text(text = "password: ayub")
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
            placeholder = { Text(text = "password: ayub") },
            shape = RoundedCornerShape(percent = 0),
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {


            CustomDefaultBtn(
                btnText = "Login Button",
                onClick = {
                    viewModel.LoginViewModel_user_loginClicked(
                        input_email,
                        input_password
                    )
                },
            )

            // Reset password
            Text(
                modifier = Modifier
                    .clickable {
                        onNavigateToForgotPassword(input_email)
                    }
                    .padding(10.dp)
                    .fillMaxWidth(),
                text = "Forgot Password ?",
                color = Color.Blue, fontSize = 15.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))


        //Register
        Text(
            modifier = Modifier
                .clickable {
                    onNavigateToRegistration(input_email)
                }
                .padding(10.dp)
                .fillMaxWidth(),
            text = "Create new Account ",
            color = Color.Blue, fontSize = 15.sp,
        )


        // Results........
        Text(
            text = "" + textValue,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        if (toAuthenticatedRoute) {
            onNavigateToAuthenticatedRoute(input_email)
        }


    }


}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        viewModel(), onNavigateToAuthenticatedRoute = {},
        onNavigateToRegistration = { },
        onNavigateToForgotPassword = {}
    )
}