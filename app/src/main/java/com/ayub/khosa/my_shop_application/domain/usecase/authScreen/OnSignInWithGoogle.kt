package com.ayub.khosa.my_shop_application.domain.usecase.authScreen

import androidx.credentials.Credential
import com.ayub.khosa.my_shop_application.data.auth.repository.AuthRepository

class OnSignInWithGoogle(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(credential: Credential) =
        authRepository.onSignInWithGoogle(credential)
}