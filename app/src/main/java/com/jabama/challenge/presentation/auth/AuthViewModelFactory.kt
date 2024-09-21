package com.jabama.challenge.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jabama.challenge.domain.auth.SignInWithGithubUseCase

// AuthViewModelFactory.kt
class AuthViewModelFactory(private val signInWithGithubUseCase: SignInWithGithubUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(signInWithGithubUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
