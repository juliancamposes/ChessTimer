package com.codeandhacks.chesstimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeandhacks.chesstimer.databinding.ActivitySetTimerBinding

class SetTimer : AppCompatActivity() {
    private lateinit var binding : ActivitySetTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_timer)
    }

    override fun onResume() {
        super.onResume()
        binding = ActivitySetTimerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.setTimerBtnBack.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)

            //putExtras

            finish()
        }
    }
}