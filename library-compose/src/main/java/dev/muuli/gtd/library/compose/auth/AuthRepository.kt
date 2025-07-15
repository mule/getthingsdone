package dev.muuli.gtd.library.compose.auth

import com.google.firebase.auth.FirebaseAuth

open class AuthRepository(
    protected val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    open val currentUser get() = firebaseAuth.currentUser

    

   open fun signOut() {
        firebaseAuth.signOut()
    }
}
