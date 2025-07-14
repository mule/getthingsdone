package dev.muuli.gtd.library.compose.auth

import android.app.Activity
import androidx.activity.result.IntentSenderRequest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseUser

@Composable
fun SignInScreen(viewModel: AuthViewModel) {
    val context = LocalContext.current
    val user: FirebaseUser? by viewModel.user.collectAsState(initial = null)
    val oneTapClient: SignInClient = remember { Identity.getSignInClient(context) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val credential = Identity.getSignInClient(context)
                .getSignInCredentialFromIntent(result.data)
            val token = credential.googleIdToken
            if (token != null) {
                viewModel.onGoogleTokenReceived(token)
            }
        }
    }

    if (user == null) {
        Button(onClick = {
            val signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(
                    BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId("REPLACE_ME")
                        .setFilterByAuthorizedAccounts(false)
                        .build()
                )
                .build()
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener { result ->
                    launcher.launch(
                        IntentSenderRequest.Builder(result.pendingIntent).build()
                    )
                }
        }) {
            Text(text = "Sign in with Google", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        Button(onClick = { viewModel.signOut() }) {
            Text(text = "Sign out", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
