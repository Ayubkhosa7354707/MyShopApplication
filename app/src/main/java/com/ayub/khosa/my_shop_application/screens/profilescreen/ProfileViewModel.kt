package com.ayub.khosa.my_shop_application.screens.profilescreen


import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.data.auth.repository.AuthRepository
import com.ayub.khosa.my_shop_application.domain.model.User
import com.ayub.khosa.my_shop_application.utils.Constants.Companion.PREF_FIREBASE_USERID_KEY
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {


    var user = MutableStateFlow<User>(User())
        private set


    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> get() = _uiState

    private fun updateState(newState: String) {
        _uiState.value = newState
    }


    init {
        user.value = User()
        PrintLogs.printInfo("ProfileViewModel init")
        updateState("")
        getUser()
    }

    private fun getUser() {
        if (authRepository.currentUser != null) {
            user.value.uid = authRepository.currentUser!!.uid
            user.value.displayName = authRepository.currentUser!!.displayName.toString()
            user.value.email = authRepository.currentUser!!.email.toString()
            user.value.photoUrl = authRepository.currentUser!!.photoUrl.toString()
        }
    }


    fun signOut() = viewModelScope.launch(Dispatchers.IO) {
        PrintLogs.printInfo("signOut  ProfileViewModel ")
        try {
            authRepository.signOut().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        PrintLogs.printInfo("Loading --> ")
                        updateState("Loading")
                    }

                    is Response.Success -> {
                        PrintLogs.printInfo("Success --> " + response.data.toString())
                        user.value = User()
                        sharedPreferences.edit().remove(PREF_FIREBASE_USERID_KEY).apply()
                        updateState("Success")
                    }

                    is Response.Error -> {
                        PrintLogs.printInfo("Error --> " + response.message)
                        updateState("Error")
                    }

                    Response.Idle -> {}
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            PrintLogs.printD("Exception  " + e.message)
        }

    }


}