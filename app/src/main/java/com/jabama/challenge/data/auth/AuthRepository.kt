package com.jabama.challenge.data.auth

import com.jabama.challenge.presentation.AuthCallback
import com.jabama.challenge.presentation.auth.MainActivity

// AuthRepository.kt
interface AuthRepository {
    fun signInWithGithub(githubId: String, callback: AuthCallback, activity: MainActivity)
}
