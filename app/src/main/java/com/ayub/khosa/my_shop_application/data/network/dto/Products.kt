package com.ayub.khosa.my_shop_application.data.network.dto

data class Products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)