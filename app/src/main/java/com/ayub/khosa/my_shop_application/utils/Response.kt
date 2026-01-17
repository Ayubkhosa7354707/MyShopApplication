package com.ayub.khosa.my_shop_application.utils

sealed class Response<out T> {
    object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T
    ) : Response<T>()

    data class Error(
        val message: String
    ) : Response<Nothing>()

    object Idle : Response<Nothing>()
}