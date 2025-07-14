package dev.muuli.gtd.library.compose.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(repository.currentUser)
    val user: StateFlow<FirebaseUser?> = _user

    fun onGoogleTokenReceived(idToken: String) {
        viewModelScope.launch {
            repository.signInWithGoogle(idToken)
            _user.value = repository.currentUser
        }
    }

    fun signOut() {
        repository.signOut()
        _user.value = null
    }
}
