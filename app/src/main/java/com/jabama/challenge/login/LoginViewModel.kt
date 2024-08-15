package com.jabama.challenge.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import com.jabama.challenge.login.model.LoginError
import com.jabama.challenge.login.model.LoginUiState
import com.jabama.challenge.core.token.repository.TokenRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    initialUiState: LoginUiState,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    private val _searchPageEvent = MutableSharedFlow<Unit>()
    val searchPageEvent = _searchPageEvent.asSharedFlow()

    fun onReceivedCode(code: String?) {
        if (code.isNullOrEmpty()) {
            noCodeErrorState()
            return
        }

        fetchAccessToken(code)
    }

    private fun fetchAccessToken(code: String) {
        _uiState.update {
            it.copy(
                isLoading = true,
                authorizeCode = code,
            )
        }

        viewModelScope.launch {
            tokenRepository.fetchAccessToken(authorizeCode = code)
                .collect { result ->
                    when (result) {
                        is Result.Success -> _searchPageEvent.emit(Unit)

                        is Result.Failure -> {
                            val error = when (result.error) {
                                is GeneralError.ApiError -> LoginError.HTTP
                                is GeneralError.NetworkError -> LoginError.CONNECTION
                                is GeneralError.UnknownError -> LoginError.UNKNOWN
                            }
                            _uiState.update {
                                it.copy(error = error, isLoading = false)
                            }
                        }
                    }
                }
        }
    }

    private fun noCodeErrorState() {
        _uiState.update {
            it.copy(
                error = LoginError.NO_CODE,
                authorizeCode = null,
                isLoading = false
            )
        }
    }

    fun onResume() {
        if (uiState.value.authorizeCode.isNullOrEmpty().not()) {
            return
        }

        noCodeErrorState()
    }

    fun onErrorButtonClick() {
        val state = uiState.value
        if (state.authorizeCode.isNullOrEmpty()) {
            _uiState.update { it.copy(isLoading = true, authorizeCode = null) }
            return
        }
        fetchAccessToken(state.authorizeCode)
    }
}
