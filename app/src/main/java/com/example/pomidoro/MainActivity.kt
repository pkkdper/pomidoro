package com.example.pomidoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

abstract class MainActivity : AppCompatActivity() {
    private lateinit var buttonStart: Button
    private lateinit var view_timer: Chronometer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.button_start)
        view_timer.isCountDown = true
        view_timer.base = SystemClock.elapsedRealtime() + 20000
        view_timer.start()
    }
}