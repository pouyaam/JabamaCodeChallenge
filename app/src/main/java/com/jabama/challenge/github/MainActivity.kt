package com.jabama.challenge.github

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jabama.challenge.github.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.authorize.setOnClickListener { view ->
            val url = "https://github.com/login/oauth/authorize?client_id=${BuildConfig.CLIENT_ID}&redirect_uri=${BuildConfig.REDIRECT_URI}&scope=repo user&state=0"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
