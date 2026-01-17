package com.ayub.khosa.my_shop_application.data.network.dto

data class Product(
    var name: String = "",
    var category: String = "",
    var description: String = "",
    var id: Int = 0,
    var price: Int = 0,
    var img: String = "",
)

