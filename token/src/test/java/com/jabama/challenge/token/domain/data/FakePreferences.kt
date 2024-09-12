package com.jabama.challenge.token.domain.data

import com.jabama.challenge.token.domain.preferences.AbstractPreferences

class FakePreferences : AbstractPreferences {

    var savedToken: String? = null

    override fun saveToken(token: String) {
        savedToken = token
    }

    override fun readToken(): String {
        return savedToken.toString()
    }
}