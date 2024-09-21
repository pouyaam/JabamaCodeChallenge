package com.jabama.challenge.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jabama.challenge.github.databinding.ActivityMainBinding
import com.jabama.challenge.presentation.search.SearchActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authViewModel.user.observe(this) { user ->
            navigateToSearchActivity(user.displayName)
            showToast("Login Successfully")
        }

        authViewModel.error.observe(this) { error ->
            showToast("Error: $error")
        }

        binding.githubLoginBtn.setOnClickListener {
            val githubId = binding.githubId.text.toString()
            if (githubId.isEmpty()) {
                showToast("Enter your GitHub ID")
            } else {
                authViewModel.signIn(githubId, this)
            }
        }
    }

    private fun navigateToSearchActivity(githubUserName: String?) {
        Intent(this, SearchActivity::class.java).apply {
            putExtra("githubUserName", githubUserName)
        }.also {
            startActivity(it)
        }
    }

    private fun showToast(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
