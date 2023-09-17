package com.example.pomidoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import java.util.Timer
import java.util.TimerTask
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var buttonStart: Button
    private lateinit var viewTimer: Chronometer
    private var countdownTimer: CountDownTimer? = null
    private lateinit var durationRadioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.button_start)
        viewTimer = findViewById(R.id.view_timer)
        viewTimer.isCountDown = true
        durationRadioGroup = findViewById(R.id.durationRadioGroup)

        countdownTimer = object : CountDownTimer(20000, 1000) { // 20000 milliseconds = 20 seconds
            override fun onTick(millisUntilFinished: Long) {
                // Update UI or perform actions on each tick if needed
            }

            override fun onFinish() {
                // Stop the Chronometer when the countdown timer finishes
                viewTimer.stop()
            }
        }

        buttonStart.setOnClickListener {
            when (durationRadioGroup.checkedRadioButtonId) {
                R.id.radioOption1 -> viewTimer.base = SystemClock.elapsedRealtime() + (30 * 60 * 1000) // 30 minutes in milliseconds
                R.id.radioOption2 -> viewTimer.base = SystemClock.elapsedRealtime() + (30 * 60 * 1000) // 35 minutes in milliseconds
                R.id.radioOption3 -> viewTimer.base = SystemClock.elapsedRealtime() + (30 * 60 * 1000) // 40 minutes in milliseconds
                // Add more cases for other options
                else -> Toast.makeText(this, "Please select a duration.", Toast.LENGTH_SHORT).show()
            }
            countdownTimer?.start()
            viewTimer.start()
        }

    }
}

