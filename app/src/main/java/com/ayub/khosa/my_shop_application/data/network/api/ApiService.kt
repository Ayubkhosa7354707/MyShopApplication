package com.ayub.khosa.my_shop_application.data.network.api

import com.ayub.khosa.my_shop_application.data.network.dto.Categories
import com.ayub.khosa.my_shop_application.data.network.dto.Product
import com.ayub.khosa.my_shop_application.data.network.dto.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("api/product/read.php")
    suspend fun getProductsListFromApi(): Products


    @GET("api/category/read.php")
    suspend fun getAllCategoriesListFromApi(): Categories


    @GET("api/product/read_products_by_category.php")
    suspend fun getProductsListByCategoryNameFromApi(
        @Query("name") name: String,
    ): Products


    @GET("api/product/read_one.php")
    suspend fun getSingleProductByIdFromApi(
        @Query("id") id: String,
    ): Product


}