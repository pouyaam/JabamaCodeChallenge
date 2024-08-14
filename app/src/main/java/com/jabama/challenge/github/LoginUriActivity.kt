package com.jabama.challenge.github

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.jabama.challenge.core.coroutines.IoScopeNamed
import com.jabama.challenge.core.coroutines.MainScopeNamed
import com.jabama.challenge.core.network.oauth.RequestAccessToken
import com.jabama.challenge.repository.oauth.AccessTokenDataSource
import com.jabama.challenge.repository.token.TokenRepository
import kotlinx.android.synthetic.main.login_uri_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LoginUriActivity : Activity() {
    private val tokenRepository: TokenRepository by inject()
    private val accessTokenDataSource: AccessTokenDataSource by inject()
    private val ioScope: CoroutineScope by inject(IoScopeNamed)
    private val mainScope: CoroutineScope by inject(MainScopeNamed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_uri_activity)
    }

    override fun onResume() {
        super.onResume()

        val intent = intent
        if (Intent.ACTION_VIEW == intent.action) {
            val uri = intent.data
            val code = uri?.getQueryParameter("code") ?: ""
            code.takeIf { it.isNotEmpty() }?.let { code ->
                val accessTokenJob = ioScope.launch {
                    val response = accessTokenDataSource.accessToken(
                        RequestAccessToken(
                            BuildConfig.CLIENT_ID,
                            BuildConfig.CLIENT_SECRET,
                            code,
                            BuildConfig.REDIRECT_URI,
                            "0"
                        )
                    ).await()

                    tokenRepository.saveToken(response.accessToken).await()
                }

                accessTokenJob.invokeOnCompletion {
                    mainScope.launch {
                        token.text = tokenRepository.readToken().await()
                        this.cancel()
                        accessTokenJob.cancelAndJoin()
                    }
                }
            } ?: run { finish() }
        }


    }
}