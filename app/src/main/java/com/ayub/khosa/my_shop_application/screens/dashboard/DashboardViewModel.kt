package com.ayub.khosa.my_shop_application.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.data.network.repository.NetworkRepository
import com.ayub.khosa.my_shop_application.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _products = MutableStateFlow<Response<List<Product>>>(Response.Idle)
    val products: StateFlow<Response<List<Product>>> = _products

    private val _categories = MutableStateFlow<Response<List<Category>>>(Response.Idle)
    val categories: StateFlow<Response<List<Category>>> = _categories

    init {
        getAllProducts()
        getAllCategories()

    }

    private fun getAllProducts() = viewModelScope.launch {
//        _products.value = Response.Loading
        val result = networkRepository.getProductsListFromApi()
        _products.value = result
    }
    private fun getAllCategories() = viewModelScope.launch {
//        _categories.value = Resource.Loading
        val result = networkRepository.getAllCategoriesListFromApi()
        _categories.value = result
    }



}