package com.codeandhacks.chesstimer

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import com.codeandhacks.chesstimer.database.Application.App
import com.codeandhacks.chesstimer.databinding.ActivityHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Home : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val TAG = "Reloj"
    private lateinit var timerPlayer1 : CountDownTimer
    private lateinit var timerPlayer2 : CountDownTimer
    private var hoursPlayer1 = -1L
    private var minutesPlayer1 = -1L
    private var secondsPlayer1 = -1L
    private var timePlayer1Milis = -1L
    private var hoursPlayer2 = -1L
    private var minutesPlayer2 = -1L
    private var secondsPlayer2 = -1L
    private var timePlayer2Milis = -1L
    private var hours = 0
    private var minutes = 5
    private var seconds = 0
    private var increment = 0
    private val context = this
    private var statusPause = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val extra = intent.extras!!
        hours = extra.get("hours") as Int
        minutes = extra.get("minutes") as Int
        seconds = extra.get("seconds") as Int
        increment = extra.get("increment") as Int

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
            finish()
        }

        var status = 3
        if (status != 3){
            binding.homeBtnConfigureTimer.isEnabled = false
        }




        //Setting the time (if it have started or not)
        if (timePlayer1Milis == -1L){
            hoursPlayer1 = (hours * 60 * 60 * 1000).toLong()
            minutesPlayer1 = (minutes * 60 * 1000).toLong()
            secondsPlayer1 = (seconds * 1000).toLong()
            timePlayer1Milis  = hoursPlayer1+minutesPlayer1+secondsPlayer1
        }

        if(timePlayer1Milis > 0L){
            hoursPlayer1 = (hours * 60 * 60 * 1000).toLong()
            minutesPlayer1 = (minutes * 60 * 1000).toLong()
            secondsPlayer1 = (seconds * 1000).toLong()
            timePlayer1Milis  = hoursPlayer1+minutesPlayer1+secondsPlayer1
        }

        if (timePlayer2Milis == -1L){
            hoursPlayer2 = (hours * 60 * 60 * 1000).toLong()
            minutesPlayer2 = (minutes * 60 * 1000).toLong()
            secondsPlayer2 = (seconds * 1000).toLong()
            timePlayer2Milis  = hoursPlayer2+minutesPlayer2+secondsPlayer2
        }

        if(timePlayer2Milis > 0L){
            hoursPlayer2 = (hours * 60 * 60 * 1000).toLong()
            minutesPlayer2 = (minutes * 60 * 1000).toLong()
            secondsPlayer2 = (seconds * 1000).toLong()
            timePlayer2Milis  = hoursPlayer2+minutesPlayer2+secondsPlayer2
        }

        //Converting to show

        var timeHoursAmbos = timePlayer1Milis / 60 /60 / 1000
        var timeMinutesAmbos = timePlayer1Milis / 60 / 1000 % 60
        var timeSecondsAmbos = timePlayer1Milis / 1000 % 60

        //Text for the bottons at the beggining
        if(timeHoursAmbos == 0L && timeMinutesAmbos >= 10){
            var timeMinutes = timePlayer1Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer1Milis  / 1000 % 60
            binding.homeBtnPlayer1.text = "${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos == 0L && timeMinutesAmbos >= 10 && timeSecondsAmbos < 10){
            var timeMinutes = timePlayer1Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer1Milis  / 1000 % 60
            binding.homeBtnPlayer1.text = "${timeMinutes}:0$timeSeconds"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos == 0L && timeMinutesAmbos < 10){
            var timeMinutes = timePlayer1Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer1Milis  / 1000 % 60
            binding.homeBtnPlayer1.text = "0${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos == 0L && timeMinutesAmbos < 10 && timeSecondsAmbos < 10){
            var timeMinutes = timePlayer1Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer1Milis  / 1000 % 60
            binding.homeBtnPlayer1.text = "0${timeMinutes}:0$timeSeconds"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos >= 1L && timeMinutesAmbos >= 10){
            var timeHours = timePlayer1Milis / 60 /60 / 1000
            var timeMinutes = timePlayer1Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer1Milis  / 1000 % 60
            binding.homeBtnPlayer1.text = "${timeHours}.${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos >= 1L && timeMinutesAmbos < 10){
            var timeHours = timePlayer1Milis / 60 /60 / 1000
            var timeMinutes = timePlayer1Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer1Milis  / 1000 % 60
            binding.homeBtnPlayer1.text = "${timeHours}.0${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos >= 1L && timeMinutesAmbos < 10 && timeSecondsAmbos < 10){
            var timeHours = timePlayer1Milis / 60 /60 / 1000
            var timeMinutes = timePlayer1Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer1Milis  / 1000 % 60
            binding.homeBtnPlayer1.text = "${timeHours}.0${timeMinutes}:0$timeSeconds"
            binding.homeBtnPlayer1.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos == 0L && timeMinutesAmbos >= 10){
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos == 0L && timeMinutesAmbos >= 10 && timeSecondsAmbos < 10){
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "${timeMinutes}:0$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos == 0L && timeMinutesAmbos < 10){
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "0${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos == 0L && timeMinutesAmbos < 10 && timeSecondsAmbos < 10){
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "0${timeMinutes}:0$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos >= 1L && timeMinutesAmbos >= 10){
            var timeHours = timePlayer2Milis / 60 /60 / 1000
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "${timeHours}.${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos >= 1L && timeMinutesAmbos < 10){
            var timeHours = timePlayer2Milis / 60 /60 / 1000
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "${timeHours}.0${timeMinutes}:$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos >= 1L && timeMinutesAmbos < 10 && timeSecondsAmbos < 10){
            var timeHours = timePlayer2Milis / 60 /60 / 1000
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "${timeHours}.0${timeMinutes}:0$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }

        if(timeHoursAmbos >= 1L && timeMinutesAmbos >= 10 && timeSecondsAmbos < 10){
            var timeHours = timePlayer2Milis / 60 /60 / 1000
            var timeMinutes = timePlayer2Milis  / 60 / 1000 % 60
            var timeSeconds = timePlayer2Milis  / 1000 % 60
            binding.homeBtnPlayer2.text = "$timeHours.$timeMinutes:0$timeSeconds"
            binding.homeBtnPlayer2.setTextColor(Color.WHITE)
        }


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
                timerPlayer2 = object : CountDownTimer(timePlayer2Milis, 100){
                    override fun onTick(p0: Long) {

                        var timeHours = p0 / 60 /60 / 1000
                        var timeMinutes = p0 / 60 / 1000 % 60
                        var timeSeconds = p0 / 1000 % 60

                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if (timeHours > 0 && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }

                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if (timeHours == 0L && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        binding.homeBtnPlayer2.setTextColor(Color.WHITE)
                        timePlayer2Milis = p0

                    }
                    override fun onFinish() {
                        binding.homeBtnPlayer2.setBackgroundColor(Color.RED)
                        binding.homeBtnPlayer1.isEnabled = false
                        binding.homeBtnPlayer2.isEnabled = false
                        timerPlayer1.cancel()
                        status = 3
                        statusPause = 1
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
                timerPlayer2 = object : CountDownTimer(timePlayer2Milis, 100){
                    override fun onTick(p0: Long) {

                        var timeHours = p0 / 60 /60 / 1000
                        var timeMinutes = p0 / 60 / 1000 % 60
                        var timeSeconds = p0 / 1000 % 60

                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if (timeHours > 0 && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }

                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if (timeHours == 0L && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer2.text= timeToShow
                        }
                        timePlayer2Milis = p0
                        binding.homeBtnPlayer2.setTextColor(Color.WHITE)

                    }
                    override fun onFinish() {
                        binding.homeBtnPlayer2.setBackgroundColor(Color.RED)
                        binding.homeBtnPlayer1.isEnabled = false
                        binding.homeBtnPlayer2.isEnabled = false
                        timerPlayer1.cancel()
                        status = 3
                        statusPause = 1
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


                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if (timeHours > 0 && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }

                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if (timeHours == 0L && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        timePlayer1Milis = p0
                        binding.homeBtnPlayer1.setTextColor(Color.WHITE)

                    }
                    override fun onFinish() {
                        binding.homeBtnPlayer1.setBackgroundColor(Color.RED)
                        binding.homeBtnPlayer1.isEnabled = false
                        binding.homeBtnPlayer2.isEnabled = false
                        timerPlayer2.cancel()
                        status = 3
                        statusPause = 1
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

                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if (timeHours > 0 && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "$timeHours.0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours > 0 && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "$timeHours.0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }

                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds >= 10){
                            var timeToShow = "$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes > 10 && timeSeconds < 10){
                            var timeToShow = "$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if (timeHours == 0L && timeMinutes < 10 && timeSeconds >= 10){
                            var timeToShow = "0$timeMinutes:$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }
                        if(timeHours == 0L && timeMinutes < 10 && timeSeconds < 10){
                            var timeToShow = "0$timeMinutes:0$timeSeconds"
                            binding.homeBtnPlayer1.text= timeToShow
                        }

                        timePlayer1Milis = p0
                        binding.homeBtnPlayer1.setTextColor(Color.WHITE)

                    }
                    override fun onFinish() {
                        binding.homeBtnPlayer1.setBackgroundColor(Color.RED)
                        timerPlayer2.cancel()
                        binding.homeBtnPlayer1.isEnabled = false
                        binding.homeBtnPlayer2.isEnabled = false
                        status = 3
                        statusPause = 1
                    }

                }.start()
                //Change the status
                status = 1
            }
        }

        binding.homeBtnPauseTimer.setOnClickListener {

            if(status != 3){
                timerPlayer1.cancel()
                timerPlayer2.cancel()
            }
            status = 3
            statusPause = 1
        }

        binding.homeBtnRestart.setOnClickListener {

            if(statusPause == 1){
                val intent = Intent(this, Restart::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}