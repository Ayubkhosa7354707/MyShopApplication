package com.ayub.khosa.my_shop_application.domain.usecase.authScreen



data class AuthUseCases(
    val signIn: SignIn,
    val onSignInWithGoogle: OnSignInWithGoogle,
    val signUp: SignUp,
)
