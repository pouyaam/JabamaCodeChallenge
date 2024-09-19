package com.jabama.challenge.presentation

import com.google.firebase.auth.FirebaseUser

// AuthCallback.kt
interface AuthCallback {
    fun onSuccess(user: FirebaseUser)
    fun onFailure(error: Exception)
}
