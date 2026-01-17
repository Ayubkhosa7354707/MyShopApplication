package com.ayub.khosa.my_shop_application.data.network.dto


data class Categories(
    val limit: Int,
    val categories: List<Category>,
    val skip: Int,
    val total: Int
)