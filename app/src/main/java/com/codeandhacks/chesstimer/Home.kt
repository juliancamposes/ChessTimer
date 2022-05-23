package com.codeandhacks.chesstimer

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.codeandhacks.chesstimer.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val TAG = "Reloj"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Set the buttons color at the beggining
        binding.homeBtnPlayer2.setBackgroundColor(Color.GRAY)
        binding.homeBtnPlayer1.setBackgroundColor(Color.CYAN)


        //Configure the timer
        binding.homeBtnConfigureTimer.setOnClickListener{
            val intent = Intent(this, SetTimer::class.java)
            startActivity(intent)
        }


        var status = 3
        var timePlayer1 = "3:00"
        var timePlayer2 = "3:00"


        //Show the timer

        //Buttons actions

        binding.homeBtnPlayTimer.setOnClickListener{
            //Buttons are available
            status = 0
            binding.homeBtnPlayer2.setBackgroundColor(Color.GRAY)
            binding.homeBtnPlayer1.setBackgroundColor(Color.CYAN)
        }

        binding.homeBtnPlayer1.setOnClickListener {
            if (status == 1 || status == 0) {
                //Change the colours when is enabled and not
                binding.homeBtnPlayer1.setBackgroundColor(Color.GRAY)
                binding.homeBtnPlayer2.setBackgroundColor(Color.CYAN)

                //Stop the player 1 timer

                //Start the player 2 timer

                //Change the status
                status = 2
            }
        }

        binding.homeBtnPlayer2.setOnClickListener {
            if (status == 2 || status == 0) {
                //Change the colours when is enabled and not
                binding.homeBtnPlayer2.setBackgroundColor(Color.GRAY)
                binding.homeBtnPlayer1.setBackgroundColor(Color.CYAN)

                //Stop the player 2 timer

                //Start the player 1 timer

                //Change the status
                status = 1
            }
        }

    }
}