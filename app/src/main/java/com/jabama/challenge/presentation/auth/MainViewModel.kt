package com.jabama.challenge.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

class MainViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val provider = OAuthProvider.newBuilder("github.com")
    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus
    private val _navigateToSearchActivity = MutableLiveData<String?>()
    val navigateToSearchActivity: LiveData<String?> get() = _navigateToSearchActivity

    init {
        provider.scopes = listOf("user:email")
    }

    fun signInWithGithub(githubId: String, activity: MainActivity) {
        if (auth.pendingAuthResult != null) {
            auth.pendingAuthResult?.addOnSuccessListener {
                _loginStatus.postValue("User exists")
            }?.addOnFailureListener { error ->
                _loginStatus.postValue("Error: $error")
            }
        } else {
            provider.addCustomParameter("login", githubId)
            auth.startActivityForSignInWithProvider(activity, provider.build())
                .addOnSuccessListener { authResult ->
                    val firebaseUser = authResult?.user
                    _navigateToSearchActivity.postValue(firebaseUser?.displayName)
                    _loginStatus.postValue("Login Successfully")
                }
                .addOnFailureListener { error ->
                    _loginStatus.postValue("Error: $error")
                }
        }
    }
}
