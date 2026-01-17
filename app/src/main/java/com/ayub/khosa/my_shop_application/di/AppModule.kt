package com.ayub.khosa.my_shop_application.di

import android.content.Context
import com.ayub.khosa.my_shop_application.data.auth.repository.AuthRepository
import com.ayub.khosa.my_shop_application.data.auth.repository.AuthRepositoryImpl
import com.ayub.khosa.my_shop_application.domain.usecase.authScreen.AuthUseCases
import com.ayub.khosa.my_shop_application.domain.usecase.authScreen.OnSignInWithGoogle
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }


    @Provides
    @Singleton
    fun provideFirebaseAuthInstance() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepository(
        firebaseAuth: FirebaseAuth,
    ): AuthRepository = AuthRepositoryImpl(
        firebaseAuth = firebaseAuth,
    )


    @Provides
    @Singleton
    fun providesAuthUseCases(authRepository: AuthRepository) = AuthUseCases(


        onSignInWithGoogle = OnSignInWithGoogle(authRepository),
    )

}