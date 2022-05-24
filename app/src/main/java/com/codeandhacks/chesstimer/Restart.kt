package com.codeandhacks.chesstimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.codeandhacks.chesstimer.database.Application.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Restart : AppCompatActivity() {
    private var hours = 0
    private var minutes = 5
    private var seconds = 0
    private var increment = 0
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restart)

        lifecycleScope.launchWhenStarted{
            withContext(Dispatchers.IO){
                val timeValuesPrev = App.getDB().timeValuesDao().findFirst()
                if(timeValuesPrev != null){
                    hours = timeValuesPrev.hour
                    minutes = timeValuesPrev.minutes
                    seconds = timeValuesPrev.seconds
                    increment = timeValuesPrev.increment
                } else {
                    hours = 0
                    minutes = 5
                    seconds = 0
                    increment = 0
                }

                val intent = Intent(context, Home::class.java)
                intent.putExtra("hours", hours)
                intent.putExtra("minutes", minutes)
                intent.putExtra("seconds", seconds)
                intent.putExtra("increment", increment)

                startActivity(intent)
                finish()
            }
        }

    }
}