package com.jabama.challenge.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import com.jabama.challenge.presentation.AuthCallback
import com.jabama.challenge.presentation.auth.MainActivity

// SearchRepositoryImpl.kt
class AuthRepositoryImpl(private val auth: FirebaseAuth) : AuthRepository {
    override fun signInWithGithub(
        githubId: String,
        callback: AuthCallback,
        activity: MainActivity
    ) {
        val provider = OAuthProvider.newBuilder("github.com")
        provider.addCustomParameter("login", githubId)
        provider.scopes = listOf("user:email")

        auth.startActivityForSignInWithProvider(activity, provider.build())
            .addOnSuccessListener { authResult ->
                val user = authResult?.user
                if (user != null) {
                    callback.onSuccess(user)
                } else {
                    callback.onFailure(Exception("User not found"))
                }
            }
            .addOnFailureListener { error ->
                callback.onFailure(error)
            }
    }
}
