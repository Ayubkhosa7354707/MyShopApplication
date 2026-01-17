package com.ayub.khosa.my_shop_application.data.auth.repository

import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import com.ayub.khosa.my_shop_application.domain.model.User
import com.ayub.khosa.my_shop_application.utils.PrintLogs
import com.ayub.khosa.my_shop_application.utils.Response
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser


    override suspend fun onSignInWithGoogle(credential: Credential): Flow<Response<Boolean>> =
        callbackFlow {
            try {
                this@callbackFlow.trySendBlocking(Response.Loading)
                if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)

                    val idToken = googleIdTokenCredential.idToken

                    PrintLogs.printInfo(" googleIdToken :" + idToken)


                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

                    firebaseAuth.signInWithCredential(firebaseCredential).await()
                    if (firebaseAuth.currentUser != null) {
                        val user: User = User()
                        user.uid = firebaseAuth.currentUser?.uid.toString()
                        user.email = firebaseAuth.currentUser?.email.toString()
                        user.displayName = firebaseAuth.currentUser?.displayName.toString()
                        user.photoUrl = firebaseAuth.currentUser?.photoUrl.toString()
                        PrintLogs.printInfo("User Info : " + user.toString())
                        this@callbackFlow.trySendBlocking(Response.Success(true))
                    } else {
                        this@callbackFlow.trySendBlocking(Response.Success(false))
                    }
                } else {
                    PrintLogs.printInfo("credential is not CustomCredential")
                    this@callbackFlow.trySendBlocking(Response.Error("Error -> credential is not CustomCredential"))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                PrintLogs.printE("Exception " + e.message)
                this@callbackFlow.trySendBlocking(Response.Error("Error ->" + e.message))
            }
            awaitClose {
                channel.close()
                cancel()
            }

        }


}