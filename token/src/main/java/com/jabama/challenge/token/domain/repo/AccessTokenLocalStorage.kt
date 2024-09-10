package com.jabama.challenge.token.domain.repo

import kotlinx.coroutines.Deferred

interface AccessTokenLocalStorage {
    fun saveToken(token: String) : Deferred<Unit>
    fun readToken(): Deferred<String>
}