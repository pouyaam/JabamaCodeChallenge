package com.jabama.challenge.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.data.search.model.GitHubResponse
import com.jabama.challenge.domain.search.SearchRepositoriesUseCase
import kotlinx.coroutines.launch

class SearchViewModel(private val searchRepositoriesUseCase: SearchRepositoriesUseCase) :
    ViewModel() {

    private val _repositories = MutableLiveData<List<GitHubResponse.Repository>>(emptyList())
    val repositories: LiveData<List<GitHubResponse.Repository>> = _repositories

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun searchRepositories(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = searchRepositoriesUseCase.execute(query)
                _repositories.value = response.items
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
