package dev.muuli.gtd.library.compose.auth

import com.google.firebase.auth.AuthResult
import dev.muuli.gtd.library.compose.AppMain

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseUser
import io.mockk.every
import io.mockk.mockk


/**
 * A fake implementation of AuthRepository for UI previews.
 * This avoids calling actual Firebase initialization.
 */
class FakeAuthRepository(
    private val fakeCurrentUser: FirebaseUser? = null,
    // You can add more parameters here to control other fake behaviors if needed
    // e.g., var simulateSignInError: Boolean = false
) : AuthRepository(
    // We pass a mock FirebaseAuth to the super constructor.
    // The key is that this mock will NOT call FirebaseAuth.getInstance().
    firebaseAuth = mockk(relaxed = true)
) {
    // This init block is important to ensure the mocked firebaseAuth in the
    // superclass behaves as expected by immediately reflecting our fakeCurrentUser.
    init {
        // 'super.currentUser' refers to the getter in the AuthRepository class.
        // We are telling MockK that whenever the firebaseAuth.currentUser
        // (which is what super.currentUser uses) is accessed via the mocked instance,
        // it should return our fakeCurrentUser.
        every { this@FakeAuthRepository.firebaseAuth.currentUser } returns fakeCurrentUser
    }

    // Override the currentUser to directly return the state we want for the preview.
    // This is the primary way our Fake will control the user state.
    override val currentUser: FirebaseUser?
        get() = fakeCurrentUser

    override suspend fun signInWithGoogle(idToken: String): AuthResult {
        // For previews, we might not need a real AuthResult, or a mock one.
        // You could also simulate a successful login by changing `fakeCurrentUser`
        // if your ViewModel would realistically update its state from a live AuthResult.
        // For simplicity, returning a relaxed mock is often enough.
        println("FakeAuthRepository: signInWithGoogle called with token: $idToken")
        if (fakeCurrentUser != null) { // If we want to simulate already being signed in
            return mockk<AuthResult>(relaxed = true).apply {
                every { user } returns fakeCurrentUser
            }
        }
        // Simulate a new successful login
        val mockNewUser = mockk<FirebaseUser>(relaxed = true).apply{
            every { uid } returns "newFakeUser"
            every { email } returns "newuser@example.com"
        }
        return mockk<AuthResult>(relaxed = true).apply {
            every { user } returns mockNewUser
        }
        // To simulate an error:
        // throw Exception("Simulated Google Sign-In Error")
    }

    override fun signOut() {
        // Can be empty for previews.
        // If you wanted to simulate the user becoming null, you'd need a way
        // for the ViewModel to observe this. Since AuthViewModel observes 'user'
        // which comes from 'repository.currentUser', changing 'fakeCurrentUser'
        // itself wouldn't trigger an update unless the FakeAuthRepository also
        // exposed a Flow that the ViewModel could collect.
        // For now, this is often sufficient.
        println("FakeAuthRepository: signOut called")
    }
}

// You might need to make AppMain 'internal' or ensure its visibility allows access from this file
// if AppMain is in src/main and this is in src/debug.
// Usually, if they are in the same module and package, it works fine.

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Preview(name = "App Preview - Signed Out", showBackground = true)
@Composable
fun AppMainPreviewSignedOut() {
    val fakeRepository = FakeAuthRepository(fakeCurrentUser = null)
    val fakeViewModel = AuthViewModel(fakeRepository)
    MaterialTheme {
        AppMain(viewModel = fakeViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Preview(name = "App Preview - Signed In", showBackground = true)
@Composable
fun AppMainPreviewSignedIn() {
    val mockUser = mockk<FirebaseUser>(relaxed = true).apply {
        every { uid } returns "fakeUserId"
        every { email } returns "user@example.com"
        every { displayName } returns "Test User Preview"
    }
    val fakeRepository = FakeAuthRepository(fakeCurrentUser = mockUser)
    val fakeViewModel = AuthViewModel(fakeRepository)
    MaterialTheme {
        AppMain(viewModel = fakeViewModel)
    }
}
