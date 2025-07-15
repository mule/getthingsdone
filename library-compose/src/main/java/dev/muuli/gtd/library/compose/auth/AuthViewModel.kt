package dev.muuli.gtd.library.compose.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(repository.currentUser)
    val user: StateFlow<FirebaseUser?> = _user

    fun onSignInResult() {
        _user.value = repository.currentUser
    }

    fun signOut() {
        repository.signOut()
        _user.value = null
    }
}
