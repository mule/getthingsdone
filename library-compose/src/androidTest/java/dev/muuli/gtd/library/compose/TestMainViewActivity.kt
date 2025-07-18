package dev.muuli.gtd.library.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.muuli.gtd.library.compose.auth.AuthViewModel

@AndroidEntryPoint
class TestMainViewActivity : ComponentActivity() {
    val testViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainView(testViewModel) }
    }
}
