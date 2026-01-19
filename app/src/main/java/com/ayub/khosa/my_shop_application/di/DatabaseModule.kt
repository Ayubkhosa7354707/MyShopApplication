package com.ayub.khosa.my_shop_application.di

import android.content.Context
import androidx.room.Room
import com.ayub.khosa.my_shop_application.data.local.AppDatabase
import com.ayub.khosa.my_shop_application.data.local.repositories.LocalRepository
import com.ayub.khosa.my_shop_application.data.local.repositories.LocalRepositoryImpl
import com.ayub.khosa.my_shop_application.utils.Constants.Companion.USERS_COLLECTION
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        USERS_COLLECTION
    ).build()


    @Singleton
    @Provides
    fun provideAppDao(appDatabase: AppDatabase) = appDatabase.appDao()


    @Singleton
    @Provides
    fun provideLocalRepository(impl: LocalRepositoryImpl): LocalRepository = impl

}