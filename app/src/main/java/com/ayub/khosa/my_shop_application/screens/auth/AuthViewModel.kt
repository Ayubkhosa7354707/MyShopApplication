package com.ayub.khosa.my_shop_application.screens.auth


import androidx.compose.runtime.mutableStateOf
import androidx.credentials.Credential
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.domain.usecase.authScreen.AuthUseCases
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {
    var toastMessage = mutableStateOf("")
        private set


    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> get() = _uiState

    private fun updateState(newState: String) {
        _uiState.value = newState
    }


    var isUserSignInState = mutableStateOf(false)
        private set


    init {
        PrintLogs.printInfo("AuthViewModel init")
        updateState("")
    }

    fun onSignInWithGoogle(credential: Credential) {

        isUserSignInState.value = false
        viewModelScope.launch {
            try {

                authUseCases.onSignInWithGoogle(credential).collect { response ->
                    when (response) {
                        is Response.Loading -> {

                            updateState("Loading")
                            toastMessage.value = ""
                            PrintLogs.printInfo("SignInWithGoogle Loading ")
                        }

                        is Response.Success -> {
                            PrintLogs.printInfo("SignInWithGoogle success " + response.data)
                            isUserSignInState.value = response.data
                            if (response.data) {
                                toastMessage.value = "SignInWithGoogle Successful"


                            } else {
                                toastMessage.value = "SignInWithGoogle Failed"
                            }

                            updateState("Success")
                        }

                        is Response.Error -> {
                            toastMessage.value = "SignInWithGoogle Failed"
                            PrintLogs.printInfo("SignInWithGoogle Failed " + response.message)

                            updateState("Error")
                        }

                        is Response.Idle -> {

                            updateState("Idle")
                            PrintLogs.printInfo("SignInWithGoogle Idle ")
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                PrintLogs.printD("Exception  " + e.message)
            }
        }


    }


}