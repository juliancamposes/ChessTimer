package com.codeandhacks.chesstimer

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import com.codeandhacks.chesstimer.databinding.ActivityHomeBinding
import java.sql.Time
import java.util.concurrent.TimeUnit

class Home : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val TAG = "Reloj"
    private lateinit var timerPlayer1 : CountDownTimer
    private lateinit var timerPlayer2 : CountDownTimer
    private var hoursPlayer1 = -1L
    private var minutesPlayer1 = -1L
    private var secondsPlayer1 = -1L
    private var hoursPlayer1Milis = -1L
    private var minutesPlayer1Milis = -1L
    private var secondsPlayer1Milis = -1L
    private var timePlayer1Milis = -1L


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
        binding.homeBtnPlayer1.setBackgroundColor(Color.parseColor("#00b084"))


        //Configure the timer
        binding.homeBtnConfigureTimer.setOnClickListener{
            val intent = Intent(this, SetTimer::class.java)
            startActivity(intent)
        }

        var status = 3

        hoursPlayer1 = (0 * 60 * 60).toLong()
        minutesPlayer1 = (1 * 60).toLong()
        secondsPlayer1 = (0).toLong()
        hoursPlayer1Milis = hoursPlayer1*1000
        minutesPlayer1Milis = minutesPlayer1*1000
        secondsPlayer1Milis = secondsPlayer1*1000
        timePlayer1Milis  = hoursPlayer1Milis+minutesPlayer1Milis+secondsPlayer1Milis

        if(hoursPlayer1 == 0L && minutesPlayer1/60 >= 10){
            binding.homeBtnPlayer1.text = "${minutesPlayer1/60}:$secondsPlayer1"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(hoursPlayer1 == 0L && minutesPlayer1/60 < 10){
            binding.homeBtnPlayer1.text = "0${minutesPlayer1/60}:$secondsPlayer1"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(hoursPlayer1 == 0L && minutesPlayer1/60 < 10 && secondsPlayer1 < 10){
            binding.homeBtnPlayer1.text = "0${minutesPlayer1/60}:0$secondsPlayer1"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(hoursPlayer1/3600 == 1L && minutesPlayer1/60 >= 10){
            binding.homeBtnPlayer1.text = "${hoursPlayer1/3600}.${minutesPlayer1/60}:$secondsPlayer1"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(hoursPlayer1/3600 == 1L && minutesPlayer1/60 < 10){
            binding.homeBtnPlayer1.text = "${hoursPlayer1/3600}.0${minutesPlayer1/60}:$secondsPlayer1"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(hoursPlayer1/3600 == 1L && minutesPlayer1/60 < 10 && secondsPlayer1 < 10){
            binding.homeBtnPlayer1.text = "${hoursPlayer1/3600}.0${minutesPlayer1/60}:0$secondsPlayer1"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        var hoursPlayer2 = (0 * 60 * 60).toLong()
        var minutesPlayer2 = (1 * 60).toLong()
        var secondsPlayer2 = (0).toLong()
        var hoursPlayer2milis = hoursPlayer2*1000
        var minutesPlayer2milis = minutesPlayer2*1000
        var secondsPlayer2milis = secondsPlayer2*1000
        var timePlayer2milis  = hoursPlayer2milis+minutesPlayer2milis+secondsPlayer2milis

        if(hoursPlayer2 == 0L && minutesPlayer2/60 >= 10){
            binding.homeBtnPlayer2.text = "${minutesPlayer2/60}:$secondsPlayer2"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(hoursPlayer2 == 0L && minutesPlayer2/60 < 10){
            binding.homeBtnPlayer2.text = "0${minutesPlayer2/60}:$secondsPlayer2"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(hoursPlayer2 == 0L && minutesPlayer2/60 < 10 && secondsPlayer2 < 10){
            binding.homeBtnPlayer2.text = "0${minutesPlayer2/60}:0$secondsPlayer2"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(hoursPlayer2/3600 == 1L && minutesPlayer2/60 >= 10){
            binding.homeBtnPlayer2.text = "${hoursPlayer1/3600}.${minutesPlayer2/60}:$secondsPlayer2"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(hoursPlayer2/3600 == 1L && minutesPlayer2/60 < 10){
            binding.homeBtnPlayer2.text = "${hoursPlayer1/3600}.0${minutesPlayer2/60}:$secondsPlayer2"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(hoursPlayer2/3600 == 1L && minutesPlayer2/60 < 10 && secondsPlayer2 < 10){
            binding.homeBtnPlayer2.text = "${hoursPlayer1/3600}.0${minutesPlayer2/60}:0$secondsPlayer2"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }



        //Show the timer

        //Buttons actions

        binding.homeBtnPlayTimer.setOnClickListener{
            //Buttons are available
            status = 0
            binding.homeBtnPlayer2.setBackgroundColor(Color.GRAY)
            binding.homeBtnPlayer1.setBackgroundColor(Color.parseColor("#00b084"))
        }

        binding.homeBtnPlayer1.setOnClickListener {
            if (status == 0) {
                //Change the colours when is enabled and not
                binding.homeBtnPlayer1.setBackgroundColor(Color.GRAY)
                binding.homeBtnPlayer2.setBackgroundColor(Color.parseColor("#00b084"))

                //Start the player 2 timer
                timerPlayer2 = object : CountDownTimer(timePlayer2milis, 100){
                    override fun onTick(p0: Long) {

                        var timeHours = p0 / 60 /60 / 1000
                        var timeMinutes = p0 / 60 / 1000 % 60
                        var timeSeconds = p0 / 1000 % 60


                        if(timeHours > 0 && timeMinutes > 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        } else if (timeHours > 0 && timeMinutes < 10) {
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        } else if (timeHours < 1 && timeMinutes < 10) {
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        } else {
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text = timeToShow
                        }
                        binding.homeBtnPlayer2.setTextColor(Color.WHITE)


                    }
                    override fun onFinish() {
                        binding.homeBtnPlayer2.setBackgroundColor(Color.RED)
                    }

                }.start()
                //Change the status
                status = 2
            }

            if (status == 1) {
                //Change the colours when is enabled and not
                binding.homeBtnPlayer1.setBackgroundColor(Color.GRAY)
                binding.homeBtnPlayer2.setBackgroundColor(Color.parseColor("#00b084"))

                //Stop player1Timer

                timerPlayer1.cancel()

                //Start the player 2 timer
                timerPlayer2 = object : CountDownTimer(timePlayer2milis, 100){
                    override fun onTick(p0: Long) {

                        var timeHours = p0 / 60 /60 / 1000
                        var timeMinutes = p0 / 60 / 1000 % 60
                        var timeSeconds = p0 / 1000 % 60

                        if(timeHours > 0 && timeMinutes > 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        } else if (timeHours > 0 && timeMinutes < 10) {
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        } else if (timeHours < 1 && timeMinutes < 10) {
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        } else {
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text = timeToShow
                        }

                        binding.homeBtnPlayer2.setTextColor(Color.WHITE)

                    }
                    override fun onFinish() {
                        TODO("Not yet implemented")
                    }

                }.start()
                //Change the status
                status = 2
            }
        }

        binding.homeBtnPlayer2.setOnClickListener {
            if (status == 0) {
                //Change the colours when is enabled and not
                binding.homeBtnPlayer2.setBackgroundColor(Color.GRAY)
                binding.homeBtnPlayer1.setBackgroundColor(Color.parseColor("#00b084"))

                //Start the player 1 timer
                timerPlayer1 = object : CountDownTimer(timePlayer1Milis, 100){
                    override fun onTick(p0: Long) {

                        var timeHours = p0 / 60 /60 / 1000
                        var timeMinutes = p0 / 60 / 1000 % 60
                        var timeSeconds = p0 / 1000 % 60

                        if(timeHours > 0 && timeMinutes > 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        } else if (timeHours > 0 && timeMinutes < 10) {
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        } else if (timeHours < 1 && timeMinutes < 10) {
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        } else {
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text = timeToShow
                        }
                        binding.homeBtnPlayer1.setTextColor(Color.WHITE)

                    }
                    override fun onFinish() {
                        TODO("Not yet implemented")
                    }

                }.start()
                //Change the status
                status = 1
            }

            if (status == 2) {
                //Change the colours when is enabled and not
                binding.homeBtnPlayer2.setBackgroundColor(Color.GRAY)
                binding.homeBtnPlayer1.setBackgroundColor(Color.parseColor("#00b084"))

                //Stop the player 2 timer
                timerPlayer2.cancel()

                //Start the player 1 timer

                timerPlayer1 = object : CountDownTimer(timePlayer1Milis, 100){
                    override fun onTick(p0: Long) {

                        var timeHours = p0 / 60 /60 / 1000
                        var timeMinutes = p0 / 60 / 1000 % 60
                        var timeSeconds = p0 / 1000 % 60

                        if(timeHours > 0 && timeMinutes > 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        } else if (timeHours > 0 && timeMinutes < 10) {
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        } else if (timeHours < 1 && timeMinutes < 10) {
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        } else {
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text = timeToShow
                        }
                        binding.homeBtnPlayer1.setTextColor(Color.WHITE)

                    }
                    override fun onFinish() {
                        TODO("Not yet implemented")
                    }

                }.start()
                //Change the status
                status = 1
            }
        }

        binding.homeBtnPauseTimer.setOnClickListener {
            status = 3
        }

    }
}