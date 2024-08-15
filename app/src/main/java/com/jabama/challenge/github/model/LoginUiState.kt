package com.jabama.challenge.github.model

import androidx.annotation.StringRes
import com.jabama.challenge.github.R

data class LoginUiState(
    val isLoading: Boolean,
    val authorizeCode: String?,
    val error: LoginError
) {

    val shouldOpenAuthorizePage: Boolean
        get() = isLoading && authorizeCode == null
}

enum class LoginError(
    @StringRes val message: Int,
    @StringRes val textButton: Int,
) {
    NO_CODE(message = R.string.login_error_no_code_message, textButton = R.string.authorize),
    HTTP(message = R.string.login_error_http_message, textButton = R.string.retry),
    CONNECTION(message = R.string.login_error_connection_message, textButton = R.string.retry),
    UNKNOWN(message = R.string.login_error_unknown_message, textButton = R.string.retry),
}
