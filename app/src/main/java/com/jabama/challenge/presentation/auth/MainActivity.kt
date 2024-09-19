package com.jabama.challenge.presentation.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jabama.challenge.github.databinding.ActivityMainBinding
import com.jabama.challenge.presentation.search.SearchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Observe LiveData from ViewModel
        observeViewModel()

        // Handle login button click
        binding.githubLoginBtn.setOnClickListener {
            val githubId = binding.githubId.text.toString()
            when (githubId.isEmpty()) {
                true -> showToast("Enter your GitHub ID")
                false -> viewModel.signInWithGithub(githubId = githubId, activity = this)
            }
        }
    }

    private fun observeViewModel() {
        // Observe login status updates
        viewModel.loginStatus.observe(this) { message ->
            showToast(message)
        }

        // Observe navigation to SearchActivity
        viewModel.navigateToSearchActivity.observe(this) { githubUserName ->
            githubUserName?.let {
                navigateToSearchActivity(it)
            }
        }
    }

    private fun navigateToSearchActivity(githubUserName: String) {
        Intent(this, SearchActivity::class.java).apply {
            putExtra("githubUserName", githubUserName)
        }.also {
            startActivity(it)
        }
    }

    private fun showToast(message: String) =
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
}
