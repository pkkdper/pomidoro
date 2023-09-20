package com.example.pomidoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import java.util.Timer
import java.util.TimerTask
import kotlin.properties.Delegates
import kotlin.system.measureTimeMillis

const val CHANNEL_ID = "my_notification_channel"
class MainActivity : AppCompatActivity() {
    private lateinit var buttonStart: Button
    private lateinit var viewTimer: Chronometer
    private var countdownTimer: CountDownTimer? = null
    private lateinit var durationRadioGroup: RadioGroup
    private lateinit var textViewChoice: TextView
    private var millisInFuture: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.button_start)
        viewTimer = findViewById(R.id.view_timer)
        viewTimer.isCountDown = true
        durationRadioGroup = findViewById(R.id.duration_radio_group)

//        if (durationRadioGroup.checkedRadioButtonId == R.id.radioOption1) {
//                millisInFuture = 6000
//            }
//        else if (durationRadioGroup.checkedRadioButtonId == R.id.radioOption2) {
//            millisInFuture = 12000
//        }
//        else if (durationRadioGroup.checkedRadioButtonId == R.id.radioOption3) {
//            millisInFuture = 18000
//        }

        when (durationRadioGroup.checkedRadioButtonId) {
            R.id.radioOption1 -> {
//                viewTimer.base = SystemClock.elapsedRealtime() + (1 * 6 * 1000)
                millisInFuture = (1 * 6 * 1000)
            } // 30 minutes in milliseconds
            R.id.radioOption2 -> {
//                viewTimer.base = SystemClock.elapsedRealtime() + (2 * 6 * 1000)
                millisInFuture = (2 * 6 * 1000)
            } // 35 minutes in milliseconds
            R.id.radioOption3 -> {
//                viewTimer.base = SystemClock.elapsedRealtime() + (3 * 6 * 1000)
                millisInFuture = (3 * 6 * 1000)
            } // 40 minutes in milliseconds
        }

        buttonStart.setOnClickListener {
            when (durationRadioGroup.checkedRadioButtonId) {
                R.id.radioOption1 -> {viewTimer.base = SystemClock.elapsedRealtime() + (1 * 6 * 1000)} // 30 minutes in milliseconds
                R.id.radioOption2 -> {viewTimer.base = SystemClock.elapsedRealtime() + (2 * 6 * 1000)} // 35 minutes in milliseconds
                R.id.radioOption3 -> {viewTimer.base = SystemClock.elapsedRealtime() + (3 * 6 * 1000)} // 40 minutes in milliseconds
                else -> Toast.makeText(this, "Please select a duration.", Toast.LENGTH_SHORT).show()
            }
                countdownTimer?.start()
                viewTimer.start()
                hideOptions()
        }

        countdownTimer = object : CountDownTimer(millisInFuture, 1000) { // 20000 milliseconds = 20 seconds
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                // Stop the Chronometer when the countdown timer finishes
                viewTimer.stop()
            }
        }


    }
    private fun hideOptions() {
        textViewChoice = findViewById(R.id.text_view_choice)
        textViewChoice.visibility = View.GONE
        durationRadioGroup.visibility = View.GONE
        viewTimer.visibility = View.VISIBLE
    }
}