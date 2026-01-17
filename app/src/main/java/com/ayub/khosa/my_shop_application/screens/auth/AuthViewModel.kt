package com.ayub.khosa.my_shop_application.screens.auth


import androidx.credentials.Credential
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.domain.usecase.authScreen.AuthUseCases
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {
    var toastMessage = mutableStateOf("")
        private set

    var isUserSignInState = mutableStateOf(false)
        private set



    init {
        PrintLogs.printInfo("AuthViewModel init")
    }

    fun onSignInWithGoogle(credential: Credential) {
        viewModelScope.launch {


            authUseCases.onSignInWithGoogle(credential).collect { response ->
                when (response) {
                    is Response.Loading -> {
                        toastMessage.value = ""
                    }

                    is Response.Success -> {
                        PrintLogs.printInfo("SignInWithGoogle success " + response.data)
                        isUserSignInState.value = response.data

                        toastMessage.value = "SignInWithGoogle Successful"
                    }

                    is Response.Error -> {
                        toastMessage.value = "SignInWithGoogle Failed"
                    }
                }
            }
        }


    }
}