package com.jabama.common

/**
 * Prefer name "Resource" instead of "Result" because of the confusion created by this class and Kotlin.Result
 */
sealed interface Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Error(val exception: Throwable? = null) : Resource<Nothing>
}