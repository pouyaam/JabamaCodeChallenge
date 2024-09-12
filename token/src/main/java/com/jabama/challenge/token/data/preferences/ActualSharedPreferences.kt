package com.jabama.challenge.token.data.preferences

import android.content.SharedPreferences
import com.jabama.challenge.token.domain.preferences.AbstractPreferences


class ActualSharedPreferences(
    private val sharedPreferences: SharedPreferences
) : AbstractPreferences {
    override fun saveToken(token: String) {
        sharedPreferences.edit().apply { putString(AbstractPreferences.TOKEN, token) }.apply()
    }

    override fun readToken(): String {
        return sharedPreferences.getString(AbstractPreferences.TOKEN, "") ?: ""
    }
}