package com.jabama.challenge.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.app.model.MainEvent
import com.jabama.challenge.app.model.MainUiState
import com.jabama.challenge.core.token.repository.TokenRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    initialUiState: MainUiState,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<MainEvent>(replay = 1)
    val event = _event.asSharedFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update {
            it.copy(isError = true, isLoading = false)
        }
    }

    init {
        checkToken()
    }

    private fun checkToken() {
        _uiState.update {
            it.copy(isLoading = true, isError = false)
        }

        viewModelScope.launch(exceptionHandler) {
            _event.emit(
                if (tokenRepository.token.value.isEmpty())
                    MainEvent.NavigateToLoginPage
                else
                    MainEvent.NavigateToSearchPage
            )
        }
    }

    fun retry() {
        checkToken()
    }
}
