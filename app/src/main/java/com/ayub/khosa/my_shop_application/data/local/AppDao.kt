package com.ayub.khosa.my_shop_application.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ayub.khosa.my_shop_application.data.local.models.UserCart

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserCart(userCart: UserCart)


    @Query("SELECT * FROM user_carts WHERE userId = :userId")
    suspend fun getCartByUserId(userId: String): List<UserCart>


    @Delete
    suspend fun deleteUserCartItem(userCart: UserCart)


    @Update
    suspend fun updateUserCartItem(userCart: UserCart)


    @Query("SELECT COUNT(*) FROM user_carts WHERE userId = :userId")
    suspend fun getBadgeCount(userId: String): Int
}