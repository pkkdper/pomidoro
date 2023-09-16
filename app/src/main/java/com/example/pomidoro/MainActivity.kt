package com.example.pomidoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private lateinit var buttonStart: Button
    private lateinit var viewTimer: Chronometer
    private var countdownTimer: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.button_start)
        viewTimer = findViewById(R.id.view_timer)
        viewTimer.isCountDown = true

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
            countdownTimer?.start()
            viewTimer.base = SystemClock.elapsedRealtime() + 20000
            viewTimer.start()
        }
    }
}

