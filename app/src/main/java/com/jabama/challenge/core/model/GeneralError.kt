package com.jabama.challenge.core.model

sealed class GeneralError {
  data class ApiError(val message: String?, val code: Int) : GeneralError()
  data object NetworkError : GeneralError()
  data class UnknownError(val error: Throwable) : GeneralError()
}
