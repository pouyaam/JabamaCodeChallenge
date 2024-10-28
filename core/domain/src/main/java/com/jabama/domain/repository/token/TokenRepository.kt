package com.jabama.domain.repository.token

interface TokenRepository {

    fun saveToken(token: String)
    fun readToken(): String
    fun clearToken()

}