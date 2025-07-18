package dev.muuli.gtd.library.compose

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.muuli.gtd.library.compose.auth.AuthRepository
import io.mockk.mockk

class FakeAuthRepository : AuthRepository(mockk<FirebaseAuth>(relaxed = true)) {
    private var user: FirebaseUser? = null

    override val currentUser: FirebaseUser?
        get() = user

    fun setUser(fakeUser: FirebaseUser?) {
        user = fakeUser
    }

    override fun signOut() {
        user = null
    }
}
