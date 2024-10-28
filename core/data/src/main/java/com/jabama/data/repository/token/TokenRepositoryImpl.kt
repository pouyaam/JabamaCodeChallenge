package com.jabama.data.repository.token

import android.content.SharedPreferences
import android.os.Build
import com.jabama.domain.repository.token.TokenRepository


private const val TOKEN = "TOKEN"

class TokenRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : TokenRepository {

    override fun saveToken(token: String) {
        val editor = sharedPreferences.edit().putString(TOKEN, token)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply()
        } else {
            editor.commit()
        }
    }


    override fun readToken(): String =
        sharedPreferences.getString(TOKEN, "") ?: ""

    override fun clearToken() {
        val editor = sharedPreferences.edit().clear()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply()
        } else {
            editor.commit()
        }
    }

}