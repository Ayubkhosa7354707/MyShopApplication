package com.ayub.khosa.my_shop_application.screens.cart_screen

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.data.local.repositories.LocalRepository
import com.ayub.khosa.my_shop_application.utils.Constants.Companion.PREF_FIREBASE_USERID_KEY
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import com.ayub.khosa.my_shop_application.utils.Utils
import com.ayub.khosa.my_shop_application.utils.Utils.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _userCart = MutableStateFlow<Response<List<UserCart>>>(Response.Idle)
    val userCart: StateFlow<Response<List<UserCart>>> = _userCart


    init {
        PrintLogs.printInfo("CartViewModel init")
         getCartsByUserId()
//        getBadgeCount()
    }



    private fun getCartsByUserId() = viewModelScope.launch {
        val result =
            localRepository.getUserCartByUserIdFromDb(getUserIdFromSharedPref(sharedPreferences))
        _userCart.value = result
    }

    fun deleteUserCartItem(userCart: UserCart) = viewModelScope.launch {
        localRepository.deleteUserCartFromDb(userCart = userCart)
        getCartsByUserId()
    }


}