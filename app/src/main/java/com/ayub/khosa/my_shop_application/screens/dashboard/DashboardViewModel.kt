package com.ayub.khosa.my_shop_application.screens.dashboard

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.data.local.repositories.LocalRepository
import com.ayub.khosa.my_shop_application.data.network.dto.Category
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
class DashboardViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val localRepository: LocalRepository,
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {


    var productsList: MutableState<List<Product>> =
        mutableStateOf<List<Product>>(listOf())
        private set


    var categoriesList: MutableState<List<Category>> =
        mutableStateOf<List<Category>>(listOf())
        private set

    // 1. Create a mutable map
    private var mutable_find_in_cart: MutableMap<Int, Boolean> = mutableMapOf(0 to false)
    val live_data_find_in_cart: LiveData<MutableMap<Int, Boolean>>
        get() = MutableLiveData(mutable_find_in_cart)


    init {
        PrintLogs.printInfo("DashboardViewModel init")

        getAllCategories()
    }

    private fun getAllProducts() = viewModelScope.launch(Dispatchers.IO) {
        PrintLogs.printInfo("getAllProducts  DashboardViewModel ")

        try {
            mutable_find_in_cart = mutableMapOf(0 to false)
            productsList.value = listOf()
            networkRepository.getProductsListFromApi()
                .collect { response ->
                    when (response) {
                        is Response.Loading -> {

                        }

                        is Response.Success -> {
                            PrintLogs.printInfo("Success --> " + response.data.toString())
                            response.data.forEach {
                                mutable_find_in_cart[it.id] = false
                                find_in_cart(product_id = it.id)
                            }
                            productsList.value = response.data
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


    fun getProductsListByCategoryNameFromApi(categoryName: String) =
        viewModelScope.launch(Dispatchers.IO) {


            PrintLogs.printInfo("getAllProducts  DashboardViewModel ")
            try {
                productsList.value = listOf()
                mutable_find_in_cart = mutableMapOf(0 to false)
                networkRepository.getProductsListByCategoryNameFromApi(categoryName)
                    .collect { response ->
                        when (response) {
                            is Response.Loading -> {

                            }

                            is Response.Success -> {
                                PrintLogs.printInfo("Success --> " + response.data.toString())
                                response.data.forEach {
                                    mutable_find_in_cart[it.id] = false
                                    find_in_cart(product_id = it.id)
                                }

                                productsList.value = response.data
                            }

                            is Response.Error -> {
                                PrintLogs.printE("Error -- " + response.message)
                            }

                            is Response.Idle -> {

                            }
                        }
                    }
            } catch (e: Exception) {
                e.printStackTrace()
                PrintLogs.printD("Exception  " + e.message)
            }
        }


    private fun getAllCategories() = viewModelScope.launch(Dispatchers.IO) {
        PrintLogs.printInfo("getAllCategories  DashboardViewModel ")
        try {
            categoriesList.value = listOf()
            networkRepository.getAllCategoriesListFromApi().collect { response ->
                when (response) {
                    is Response.Loading -> {
                    }

                    is Response.Success -> {
                        PrintLogs.printInfo("Success --> " + response.data.toString())
                        categoriesList.value = response.data

                        getProductsListByCategoryNameFromApi(categoriesList.value[0].name)
                    }

                    is Response.Error -> {
                        PrintLogs.printE("Error -- " + response.message)
                    }

                    is Response.Idle -> {

                    }
                }


            }
        } catch (e: Exception) {
            e.printStackTrace()
            PrintLogs.printD("Exception  " + e.message)
        }

    }

    fun addToCart(userCart: UserCart) = viewModelScope.launch {

        localRepository.insertUserCartToDb(
            userCart.copy(
                userId = getUserIdFromSharedPref(sharedPreferences)
            )
        )
        mutable_find_in_cart[userCart.productId] = true


    }


    private suspend fun find_in_cart(product_id: Int) {

        PrintLogs.printInfo("viewmodel find_in_cart  " + product_id + " ")
        try {
            var result: Boolean = localRepository.addedtocat(
                product_id,
                getUserIdFromSharedPref(sharedPreferences)
            )

            PrintLogs.printInfo("viewmodel find_in_cart  " + product_id + " " + result)
            mutable_find_in_cart[product_id] = result

        } catch (e: Exception) {
            e.printStackTrace()
            PrintLogs.printE("Error ->" + e.message)
        }
    }


    fun deleteUserCartItem(userCart: UserCart) = viewModelScope.launch {
        localRepository.deleteUserCartFromDb(userCart = userCart)
        mutable_find_in_cart[userCart.productId] = false
    }


}