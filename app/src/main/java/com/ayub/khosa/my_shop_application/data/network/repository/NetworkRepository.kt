package com.ayub.khosa.my_shop_application.data.network.repository

import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.Response

interface NetworkRepository {

    suspend fun getProductsListFromApi(): Response<List<Product>>

    suspend fun getSingleProductByIdFromApi(productId: Int): Response<Product>

    suspend fun getAllCategoriesListFromApi(): Response<List<Category>>

    suspend fun getProductsListByCategoryNameFromApi(categoryName: String): Response<List<Product>>


}