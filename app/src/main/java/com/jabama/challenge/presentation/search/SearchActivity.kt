package com.jabama.challenge.presentation.search

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jabama.challenge.github.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val authViewModel: SearchViewModel by viewModel()
    private val repositoryAdapter = RepositoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        binding.searchButton.setOnClickListener {
            val query = binding.searchEditText.text.toString()
            if (query.isNotEmpty()) {
                authViewModel.searchRepositories(query)
            } else {
                Snackbar.make(binding.root, "Enter a query", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.repositoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.repositoriesRecyclerView.adapter = repositoryAdapter
    }

    private fun observeViewModel() {
        authViewModel.repositories.observe(this) { repositories ->
            repositoryAdapter.submitList(repositories)
        }

        authViewModel.isLoading.observe(this) { isLoading ->
            // Show or hide progress bar (if you add one)
        }

        authViewModel.errorMessage.observe(this) { error ->
            error?.let {
                Log.e("SearchActivity", "Error: $it")
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
