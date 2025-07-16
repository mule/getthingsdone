package dev.muuli.gtd.library.compose.auth

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseUser

@Composable
fun SignInScreen(viewModel: AuthViewModel) {
    val user: FirebaseUser? by viewModel.user.collectAsState(initial = null)

    val launcher = rememberLauncherForActivityResult(
        contract = FirebaseAuthUIActivityResultContract()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Successfully signed in
            viewModel.onSignInResult()
        } else {
            // Sign in failed
        }
    }

    if (user == null) {
        Column {
            Button(onClick = {
                val providers = arrayListOf(
                    AuthUI.IdpConfig.GoogleBuilder().build()
                )
                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
                launcher.launch(signInIntent)
            }) {
                Text(
                    text = "Sign in with Google",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Button(onClick = {
                val providers = arrayListOf(
                    AuthUI.IdpConfig.EmailBuilder().build()
                )
                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()
                launcher.launch(signInIntent)
            }) {
                Text(
                    text = "Sign in with Email",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    } else {
        Button(onClick = { viewModel.signOut() }) {
            Text(text = "Sign out", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
