package com.jabama.challenge.core.network

import com.jabama.challenge.github.BuildConfig
import okhttp3.MediaType.Companion.toMediaType

object NetworkConstants {
    val MEDIA_TYPE = "application/json".toMediaType()

    object TimeOut {
        const val READ = 30L
        const val WRITE = 30L
        const val CONNECTION = 30L
    }

    object Header {
        val accept = "Accept" to "application/json"
    }

    object OAuth {
        const val NAME = "OAuth"
        private const val CLIENT_ID_QUERY = "client_id=${BuildConfig.CLIENT_ID}"
        private const val REDIRECT_QUERY = "redirect_uri=${BuildConfig.REDIRECT_URI}"
        private const val SCOPE_QUERY = "scope=repo user"
        private const val STATE_QUERY = "state=0"

        const val BASE_URL = "https://github.com/"
        private const val OAUTH_ENDPOINT = "login/oauth/"
        const val AUTHORIZE_URL = "$BASE_URL${OAUTH_ENDPOINT}authorize?$CLIENT_ID_QUERY&$REDIRECT_QUERY&$SCOPE_QUERY&$STATE_QUERY"
        const val ACCESS_TOKEN = "${OAUTH_ENDPOINT}access_token"
    }

    object GithubAPI {
        const val NAME = "GithubAPI"
        const val BASE_URL = "https://api.github.com/"
        const val SEARCH_REPOSITORIES = "search/repositories"
    }
}