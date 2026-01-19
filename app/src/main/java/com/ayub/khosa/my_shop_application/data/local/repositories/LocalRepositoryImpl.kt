package com.ayub.khosa.my_shop_application.data.local.repositories

import com.ayub.khosa.my_shop_application.data.local.AppDao
import com.ayub.khosa.my_shop_application.data.local.models.UserCart
import com.ayub.khosa.my_shop_application.utils.Response
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val appDao: AppDao
) : LocalRepository {

    override suspend fun getUserCartByUserIdFromDb(userId: String): Response<List<UserCart>> {
        return try {
            val result = appDao.getCartByUserId(userId = userId)
            Response.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message.toString())
        }

    }

    override suspend fun insertUserCartToDb(userCart: UserCart) {
        appDao.insertUserCart(userCart = userCart)
    }

    override suspend fun deleteUserCartFromDb(userCart: UserCart) {
        appDao.deleteUserCartItem(userCart = userCart)
    }

    override suspend fun updateUserCartFromDb(userCart: UserCart) {
        appDao.updateUserCartItem(userCart = userCart)
    }


    override suspend fun getBadgeCountFromDb(userId: String): Int {
        return appDao.getBadgeCount(userId = userId)
    }


}