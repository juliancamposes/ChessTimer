package com.codeandhacks.chesstimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            val intent = Intent(this, Home::class.java)
            intent.putExtra("Hours", 0)
            intent.putExtra("Minutes", 5)
            intent.putExtra("Seconds", 0)
            intent.putExtra("Increment", 0)
            startActivity(intent)
            finish()
        }, 3000)
    }
}