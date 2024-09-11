package com.jabama.challenge.common.utils

import com.google.gson.JsonParseException
import com.jabama.challenge.common.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


sealed class ApiCallError(val message: UiText) {
    data class AuthError(val error: UiText) : ApiCallError(error)
    data class HttpError(val code: Int, val error: UiText) : ApiCallError(error)
    data class NetworkError(val error: UiText) : ApiCallError(error)
    data class DataParseError(val error: UiText) : ApiCallError(error)
    data class UnknownError(val error: UiText) : ApiCallError(error)
}

fun Throwable?.toApiCallError(): ApiCallError {
    when (this) {
        is HttpException -> {
            return if (code() == 401)
                ApiCallError.AuthError(UiText.StringResource(R.string.auth_error))
            else
                ApiCallError.HttpError(
                    code = code(),
                    error = UiText.DynamicString(
                        response()?.errorBody()?.string().orEmpty()
                    )
                )
        }

        is SocketTimeoutException,
        is IOException ->
            return ApiCallError.NetworkError(UiText.StringResource(R.string.network_error))

        is JsonParseException ->
            return ApiCallError.DataParseError(UiText.StringResource(R.string.data_parse_error))

        else ->
            return ApiCallError.UnknownError(UiText.StringResource(R.string.unknown_error))

    }
}