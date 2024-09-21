package com.jabama.challenge.domain.auth

import com.jabama.challenge.presentation.AuthCallback
import com.jabama.challenge.presentation.auth.MainActivity

// SignInWithGithubUseCase.kt
interface SignInWithGithubUseCase {
    fun execute(githubId: String, callback: AuthCallback, activity: MainActivity)
}
