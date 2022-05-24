package com.codeandhacks.chesstimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codeandhacks.chesstimer.databinding.ActivitySetTimerBinding

class SetTimer : AppCompatActivity() {
    private lateinit var binding : ActivitySetTimerBinding
    private var hoursToSave = ""
    private var minutesToSave = ""
    private var secondsToSave = ""
    private var incrementToSave = ""
    private val TAG = "Set Time"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_timer)
    }

    override fun onResume() {
        super.onResume()
        binding = ActivitySetTimerBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        //Time from ET

        var hours = 0
        var minutes = 5
        var seconds = 0
        var increment = 0

        binding.setTimerHours.setText(hours.toString())
        binding.setTimerMinutes.setText("0${minutes}")
        binding.setTimerSeconds.setText("0${seconds}")

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
                binding.setTimerMinutes.setText("0${minutes}")
            } else {
                binding.setTimerMinutes.setText(minutes.toString())
            }
        }

        binding.setTimerBtnMinutesDown.setOnClickListener {
            if(minutes > 0){
                minutes -= 1
            }
            if(minutes < 10){
                binding.setTimerMinutes.setText("0${minutes}")
            } else {
                binding.setTimerMinutes.setText(minutes.toString())
            }
        }

        binding.setTimerBtnSecondsUp.setOnClickListener {
            if(seconds < 60){
                seconds += 1
            }
            if(seconds < 10){
                binding.setTimerSeconds.setText("0${seconds}")
            } else {
                binding.setTimerSeconds.setText(seconds.toString())
            }
        }

        binding.setTimerBtnSecondsDown.setOnClickListener {
            if(seconds > 0){
                seconds -= 1
            }

            if(seconds < 10){
                binding.setTimerSeconds.setText("0${seconds}")
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
            binding.setTimerMinutes.setText("0${minutes}")
            binding.setTimerSeconds.setText("0${seconds}")
        }

        binding.setTimerBtn2.setOnClickListener {
            hours = 0
            minutes = 2
            seconds = 0
            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText("0${minutes}")
            binding.setTimerSeconds.setText("0${seconds}")
        }

        binding.setTimerBtn3.setOnClickListener {
            hours = 0
            minutes = 3
            seconds = 0

            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText("0${minutes}")
            binding.setTimerSeconds.setText("0${seconds}")
        }

        binding.setTimerBtn4.setOnClickListener {
            hours = 0
            minutes = 5
            seconds = 0
            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText("0${minutes}")
            binding.setTimerSeconds.setText("0${seconds}")
        }

        binding.setTimerBtn5.setOnClickListener {
            hours = 0
            minutes = 10
            seconds = 0

            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText(minutes.toString())
            binding.setTimerSeconds.setText("0${seconds}")
        }

        binding.setTimerBtn6.setOnClickListener {
            hours = 0
            minutes = 15
            seconds = 0

            binding.setTimerHours.setText(hours.toString())
            binding.setTimerMinutes.setText(minutes.toString())
            binding.setTimerSeconds.setText("0${seconds}")
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
            intent.putExtra("Hours", hoursToSave)
            intent.putExtra("Minutes", minutesToSave)
            intent.putExtra("Seconds", secondsToSave)
            intent.putExtra("Increment", incrementToSave)

            startActivity(intent)
            //putExtras
        }

        binding.setTimerBtnSave.setOnClickListener {
            hoursToSave = binding.setTimerHours.text.toString()
            minutesToSave = binding.setTimerMinutes.text.toString()
            secondsToSave = binding.setTimerSeconds.text.toString()
            incrementToSave = "0"
            Log.d(TAG, minutesToSave)

        }
    }
}