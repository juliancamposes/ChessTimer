package com.codeandhacks.chesstimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        val TAG = "Set"
        setContentView(view)

        //Time from ET

        var hours = 0
        var minutes = 5
        var seconds = 0
        var increment = 0

        binding.setTimerHours.setText(hours.toString())
        binding.setTimerMinutes.setText("0${minutes.toString()}")
        binding.setTimerSeconds.setText("0${seconds.toString()}")

        //Arrow buttons

        binding.setTimerBtnHoursUp.setOnClickListener{
            hours += 1
            binding.setTimerHours.setText(hours.toString())
        }

        binding.setTimerBtnHoursDown.setOnClickListener {
            if(hours > 0){
                hours -= 1
                binding.setTimerHours.setText(hours.toString())
            }
        }

        binding.setTimerBtnMinutesUp.setOnClickListener {
            if(minutes < 60){
                minutes += 1
            }
            if(minutes < 10){
                binding.setTimerMinutes.setText("0${minutes.toString()}")
            } else {
                binding.setTimerMinutes.setText(minutes.toString())
            }
        }

        binding.setTimerBtnMinutesDown.setOnClickListener {
            if(minutes > 0){
                minutes -= 1
            }
            if(minutes < 10){
                binding.setTimerMinutes.setText("0${minutes.toString()}")
            } else {
                binding.setTimerMinutes.setText(minutes.toString())
            }
        }

        binding.setTimerBtnSecondsUp.setOnClickListener {
            if(seconds < 60){
                seconds += 1
            }
            if(seconds < 10){
                binding.setTimerSeconds.setText("0${seconds.toString()}")
            } else {
                binding.setTimerSeconds.setText(seconds.toString())
            }
        }

        binding.setTimerBtnSecondsDown.setOnClickListener {
            if(seconds > 0){
                seconds -= 1
            }

            if(seconds < 10){
                binding.setTimerSeconds.setText("0${seconds.toString()}")
            } else {
                binding.setTimerSeconds.setText(seconds.toString())
            }

        }

        //Buttons with time

        binding.setTimerBtn1.setOnClickListener {
            hours = 0
            minutes = 1
            seconds = 0
            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText("0${minutes.toString()}")
            binding.setTimerSeconds.setText("0${seconds.toString()}")
        }

        binding.setTimerBtn2.setOnClickListener {
            hours = 0
            minutes = 2
            seconds = 0
            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText("0${minutes.toString()}")
            binding.setTimerSeconds.setText("0${seconds.toString()}")
        }

        binding.setTimerBtn3.setOnClickListener {
            hours = 0
            minutes = 3
            seconds = 0

            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText("0${minutes.toString()}")
            binding.setTimerSeconds.setText("0${seconds.toString()}")
        }

        binding.setTimerBtn4.setOnClickListener {
            hours = 0
            minutes = 5
            seconds = 0
            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText("0${minutes.toString()}")
            binding.setTimerSeconds.setText("0${seconds.toString()}")
        }

        binding.setTimerBtn5.setOnClickListener {
            hours = 0
            minutes = 10
            seconds = 0

            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText(minutes.toString())
            binding.setTimerSeconds.setText("0${seconds.toString()}")
        }

        binding.setTimerBtn6.setOnClickListener {
            hours = 0
            minutes = 15
            seconds = 0

            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText(minutes.toString())
            binding.setTimerSeconds.setText("0${seconds.toString()}")
        }

        binding.setTimerSelectorIncrement.setOnClickListener {

            if(binding.setTimerSelectorIncrement.isChecked){
                //Alert dialog to chose the increment

            }

            if(binding.setTimerSelectorIncrement.isChecked == false){
                increment = 0
            }
        }

        binding.setTimerBtnBack.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            //putExtras

            finish()
        }

        binding.setTimerBtnSave.setOnClickListener {
            val hoursToSave = binding.setTimerHours.toString()
            val minutesToSave = binding.setTimerMinutes.toString()
            val secondsToSave = binding.setTimerSeconds.toString()

            val intent = Intent(this, Home::class.java)
            intent.putExtra("Hours", hoursToSave)
            intent.putExtra("Minutes", minutesToSave)
            intent.putExtra("Seconds", secondsToSave)
            intent.putExtra("Increment", increment)
            startActivity(intent)
        }
    }
}