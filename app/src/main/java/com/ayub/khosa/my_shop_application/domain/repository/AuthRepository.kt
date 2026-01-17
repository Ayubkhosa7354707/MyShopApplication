package com.ayub.khosa.my_shop_application.domain.repository



import androidx.credentials.Credential
import com.ayub.khosa.my_shop_application.utils.Response
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun signIn(email: String, password: String): Flow<Response<Boolean>>
    suspend fun onSignInWithGoogle(credential: Credential): Flow<Response<Boolean>>
    suspend fun signUp(email: String, password: String): Flow<Response<Boolean>>

}