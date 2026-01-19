package com.ayub.khosa.my_shop_application.data.local.repositories

import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.utils.Response

interface LocalRepository {

    suspend fun getUserCartByUserIdFromDb(userId: String): Response<List<UserCart>>

    suspend fun insertUserCartToDb(userCart: UserCart)

    suspend fun deleteUserCartFromDb(userCart: UserCart)

    suspend fun updateUserCartFromDb(userCart: UserCart)

    suspend fun getBadgeCountFromDb(userId: String): Int
}