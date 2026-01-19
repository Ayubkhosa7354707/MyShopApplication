package com.ayub.khosa.my_shop_application.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayub.khosa.my_shop_application.data.local.models.UserCart


@Database(entities = [UserCart::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}