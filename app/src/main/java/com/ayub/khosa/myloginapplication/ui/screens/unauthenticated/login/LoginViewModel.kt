package com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myloginapplication.model.USER
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.utils.NetworkHelper
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val repository: MainActivityRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {





    //    var user = MutableLiveData<USER>()
    private val _uiStatetextValue = MutableStateFlow("")
    val textValue: StateFlow<String> = _uiStatetextValue
    fun updateState(newValue: String) {
        _uiStatetextValue.value = newValue
        PrintLogs.printD(" LoginViewModel updateState " + newValue)
    }

    private val _ToAuthenticatedRoute = MutableStateFlow(false)
    val toAuthenticatedRoute: StateFlow<Boolean> = _ToAuthenticatedRoute
    fun toAuthenticatedRouteState(newValue: Boolean) {
        _ToAuthenticatedRoute.value = newValue
        PrintLogs.printD(" LoginViewModel ToAuthenticatedRouteState " + newValue)
    }


    init {
        PrintLogs.printD("LoginViewModel init")
    }


    fun LoginViewModel_user_loginClicked(email: String, password: String) {
        updateState("")

        PrintLogs.printD("LoginViewModel  LoginViewModel_user_loginClicked")
        PrintLogs.printD("LoginViewModel  email " + email)
        PrintLogs.printD("LoginViewModel password " + password)


        viewModelScope.launch {

            try {
                if (!email.isEmpty() && !password.isEmpty()) {
                    if (networkHelper.isNetworkConnected()) {
                        val response = repository.getLoginUser(
                            email, password
                        )

                        //   updateState(response.toString())


                        PrintLogs.printD(" onResponse Success :  " + response.response)
                        PrintLogs.printD(" onResponse Success :  " + response.data)
                        PrintLogs.printD(" onResponse Success :  " + response.error)
                        if (response.response == "Success") {

                            PrintLogs.printD(" onResponse Success data email :  " + response.data.email_id)
                            PrintLogs.printD(" onResponse Success data first_name :  " + response.data.first_name)
                            PrintLogs.printD(" onResponse Success data last_name :  " + response.data.last_name)
                            PrintLogs.printD(" onResponse Success data user_id :  " + response.data.user_id)
                            PrintLogs.printD(" onResponse Success data password :  " + response.data.password)
                            PrintLogs.printD(" onResponse Success data tokencode :  " + response.data.tokenCode)
                            //   updateState(response.data.toString())

                            //  user.postValue(response.data)
                            addUserinDB(response.data)

                            updateState(response.data.email_id)
                            toAuthenticatedRouteState(true)
                        } else {
                            updateState(response.error.toString())

                        }
                    } else {

                        updateState("No internet")
                    }
                } else {
                    updateState("Please Enter  user email or password")
                    PrintLogs.printD("Please Enter  user email or password")

                }
            } catch (e: Exception) {

                updateState("Exception  " + e.message)
                PrintLogs.printD("Exception  " + e.message)

            }
        }


    }

    fun addUserinDB(user: USER) {
        PrintLogs.printD(" LoginViewModel addUserinDB ")
        try {
            if (repository.fetchUSERByName(user.email_id, user.password) != null) {
                repository.updateUSERinDB(user)
            } else {
                repository.insertUSERinDB(user)
            }
        } catch (e: Exception) {

            updateState("Exception " + e.message)
            PrintLogs.printD("Exception: ${e.message}")
        }
    }


}
