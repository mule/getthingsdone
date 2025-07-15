package dev.muuli.gtd.library.compose.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await

open class AuthRepository(
    protected val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    open val currentUser get() = firebaseAuth.currentUser

    open suspend fun signInWithGoogle(idToken: String): AuthResult {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return firebaseAuth.signInWithCredential(credential).await()
    }

   open fun signOut() {
        firebaseAuth.signOut()
    }
}
