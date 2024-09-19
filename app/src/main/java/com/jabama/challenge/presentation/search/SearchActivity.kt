package com.jabama.challenge.presentation.search

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jabama.challenge.github.R
import com.jabama.challenge.presentation.auth.MainActivity

class SearchActivity : AppCompatActivity() {

    var userName = ""

    private lateinit var githubUserName: TextView
    private lateinit var logoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        githubUserName = findViewById(R.id.id)
        logoutBtn = findViewById(R.id.logOut)
        userName = intent.getStringExtra("githubUserName")!!
        githubUserName.text = userName

        logoutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }
}
