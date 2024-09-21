package com.jabama.challenge.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.jabama.challenge.domain.auth.SignInWithGithubUseCase
import com.jabama.challenge.presentation.AuthCallback

// AuthViewModel.kt
class AuthViewModel(private val signInWithGithubUseCase: SignInWithGithubUseCase) : ViewModel() {

    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> get() = _user

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> get() = _error

    fun signIn(githubId: String, activity: MainActivity) {
        signInWithGithubUseCase.execute(
            githubId = githubId,
            callback = object : AuthCallback {
                override fun onSuccess(user: FirebaseUser) {
                    _user.postValue(user)
                }

                override fun onFailure(error: Exception) {
                    _error.postValue(error)
                }
            },
            activity = activity
        )
    }
}
