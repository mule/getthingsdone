package dev.muuli.gtd.library.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import dev.muuli.gtd.library.compose.auth.AuthViewModel
import dev.muuli.gtd.library.compose.auth.SignInScreen

@AndroidEntryPoint
class MainViewActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainView(viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun MainView(viewModel: AuthViewModel) {
    val user by viewModel.user.collectAsState()

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        val titleText = if (user != null) {
                            user?.email?.split("@")?.get(0) ?: "Unknown user"
                        } else {
                            "Get Things Done"
                        }
                        Text(text = titleText)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },
            containerColor = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
                    .padding(horizontal = 8.dp)
            ) {
                if (user == null) {
                    SignInScreen(viewModel)
                } else {
                    AuthenticatedMainScreen(user)
                }
            }
        }
    }
}

@Composable
fun AuthenticatedMainScreen(user: FirebaseUser?) {
    val displayText = when {
        user?.email?.isNotBlank() == true -> "You are logged in as ${user.email}"
        user != null -> "You are logged in. (Email not available)"
        else -> "Not currently logged in."
    }
    Log.d("AuthScreen", "User: ${user?.uid}, Email: ${user?.email}, DisplayText: $displayText")
    Text(text = displayText)
}
