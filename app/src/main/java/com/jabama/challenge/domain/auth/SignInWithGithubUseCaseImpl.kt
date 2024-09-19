package com.jabama.challenge.domain.auth

import com.jabama.challenge.data.auth.AuthRepository
import com.jabama.challenge.presentation.AuthCallback
import com.jabama.challenge.presentation.auth.MainActivity

// SignInWithGithubUseCaseImpl.kt
class SignInWithGithubUseCaseImpl(private val authRepository: AuthRepository) :
    SignInWithGithubUseCase {
    override fun execute(githubId: String, callback: AuthCallback, activity: MainActivity) {
        authRepository.signInWithGithub(githubId, callback, activity)
    }
}
