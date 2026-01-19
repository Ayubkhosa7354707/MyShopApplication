package com.ayub.khosa.my_shop_application.screens.productdetail

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.data.local.repositories.LocalRepository
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.data.network.repository.NetworkRepository
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import com.ayub.khosa.my_shop_application.utils.Utils.getUserIdFromSharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var product: MutableState<Product> = mutableStateOf<Product>(Product())
        private set

    init {
        PrintLogs.printInfo("ProductDetailViewModel init")
    }


    fun getSingleProductByIdFromApi(productId: String) = viewModelScope.launch(Dispatchers.IO) {
        PrintLogs.printInfo("getAllProducts  DashboardViewModel ")
        try {
            networkRepository.getSingleProductByIdFromApi(Integer.parseInt(productId))
                .collect { response ->
                    when (response) {
                        is Response.Loading -> {

                        }

                        is Response.Success -> {
                            PrintLogs.printInfo("Success --> " + response.data.toString())
                            product.value = response.data
                        }

                        is Response.Error -> {
                            PrintLogs.printE("Error -- " + response.message)
                        }

                        is Response.Idle -> {

                        }
                    }
                }
        } catch (e: Exception) {
            PrintLogs.printD("Exception  " + e.message)
        }
    }


    fun addToCart(userCart: UserCart) = viewModelScope.launch {

        localRepository.insertUserCartToDb(
            userCart.copy(
                userId = getUserIdFromSharedPref(sharedPreferences)
            )
        )
    }
}