package com.ayub.khosa.my_shop_application.domain.usecase.authScreen
 import com.ayub.khosa.my_shop_application.domain.repository.AuthRepository

class SignIn(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signIn(email, password)
}