package com.jabama.challenge.token.domain.preferences

interface AbstractPreferences {

    companion object {
        const val TOKEN = "TOKEN"
    }

    fun saveToken(token: String)
    fun readToken(): String

}