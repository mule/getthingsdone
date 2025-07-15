package dev.muuli.gtd.library.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue // <-- Make sure this import is present
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.muuli.gtd.library.compose.ui.components.Factorial
import dev.muuli.gtd.library.compose.auth.AuthViewModel
import dev.muuli.gtd.library.compose.auth.SignInScreen
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.muuli.gtd.library.compose.auth.AuthRepository
import io.mockk.every
import io.mockk.mockk



class ComposeActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // In a real app, you might use Hilt or another DI framework
        // to provide the AuthViewModel and its dependencies.
        val authRepository = AuthRepository(FirebaseAuth.getInstance())
        val viewModel = AuthViewModel(authRepository)
        setContent {
            AppMain(viewModel)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun AppMain(viewModel: AuthViewModel) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Kotlin Android Template") },
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
                val user by viewModel.user.collectAsState()
                if (user == null) {
                    SignInScreen(viewModel)
                } else {
                    Factorial()
                }
            }
        }
    }
}






