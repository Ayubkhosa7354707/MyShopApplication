package com.ayub.khosa.my_shop_application.data.network.repository

import com.ayub.khosa.my_shop_application.data.network.dto.Category
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.utils.Response
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    suspend fun getProductsListFromApi(): Flow<Response<List<Product>>>

    suspend fun getSingleProductByIdFromApi(productId: Int): Flow<Response<Product>>

    suspend fun getAllCategoriesListFromApi(): Flow<Response<List<Category>>>

    suspend fun getProductsListByCategoryNameFromApi(categoryName: String): Flow<Response<List<Product>>>


}