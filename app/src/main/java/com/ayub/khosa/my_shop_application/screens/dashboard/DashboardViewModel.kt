package com.ayub.khosa.my_shop_application.screens.dashboard

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.data.network.repository.NetworkRepository
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {


    var productsList: MutableState<List<Product>> =
        mutableStateOf<List<Product>>(listOf())
        private set
    var categoriesList: MutableState<List<Category>> =
        mutableStateOf<List<Category>>(listOf())
        private set


    init {

        PrintLogs.printInfo("DashboardViewModel init")
        getAllProducts()
        getAllCategories()
    }

    private fun getAllProducts() = viewModelScope.launch(Dispatchers.IO) {
        PrintLogs.printInfo("getAllProducts  DashboardViewModel ")
        try {
            productsList.value = listOf()
            networkRepository.getProductsListFromApi()
                .collect { response ->
                    when (response) {
                        is Response.Loading -> {

                        }

                        is Response.Success -> {
                            PrintLogs.printInfo("Success --> " + response.data.toString())
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
                networkRepository.getProductsListByCategoryNameFromApi(categoryName)
                    .collect { response ->
                        when (response) {
                            is Response.Loading -> {

                            }

                            is Response.Success -> {
                                PrintLogs.printInfo("Success --> " + response.data.toString())
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


}