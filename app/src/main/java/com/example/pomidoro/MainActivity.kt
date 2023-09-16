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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.button_start)
        viewTimer = findViewById(R.id.view_timer)
        viewTimer.isCountDown = true
        buttonStart.setOnClickListener{
            viewTimer.base = SystemClock.elapsedRealtime() + 20000
            viewTimer.start()
        }
    }
}

