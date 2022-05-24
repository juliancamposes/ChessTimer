package com.codeandhacks.chesstimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Restart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restart)
        val intent = Intent(this, Home::class.java)
        startActivity(intent)
        finish()
    }
}