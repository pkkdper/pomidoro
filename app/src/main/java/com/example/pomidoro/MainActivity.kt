package com.example.pomidoro

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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
import androidx.core.app.NotificationCompat
import java.util.Timer
import java.util.TimerTask
import kotlin.properties.Delegates
import kotlin.system.measureTimeMillis

const val CHANNEL_ID = "my_notification_channel"
class MainActivity : AppCompatActivity() {
    private lateinit var buttonStart: Button
    private lateinit var viewTimer: Chronometer
    private var countdownTimer: CountDownTimer? = null
    private var countdownTimerBreak: CountDownTimer? = null
    private lateinit var durationRadioGroup: RadioGroup
    private lateinit var textViewChoice: TextView
    private var millisInFuture: Long = 0
    private var mode = "work"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.button_start)
        viewTimer = findViewById(R.id.view_timer)
        viewTimer.isCountDown = true
        durationRadioGroup = findViewById(R.id.duration_radio_group)

        fun showNotification() {
            val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Timer Finished")
                .setContentText("Your timer has reached 00:00.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            // Check if the channel exists, and if not, create it
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    "Timer Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }

            // Show the notification
            notificationManager.notify(1, notificationBuilder.build())
        }

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
                if (mode == "work") {
                    countdownTimer?.start()
                    viewTimer.start()
                    hideOptions()
                    Log.e("WORK", "WORK")
                }
                if (mode == "break") {
                    countdownTimerBreak?.start()
                    viewTimer.start()
                    Log.e("Break", "Break")
                }
        }

        countdownTimer = object : CountDownTimer(millisInFuture, 1000) { // 20000 milliseconds = 20 seconds
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                // Stop the Chronometer when the countdown timer finishes
                viewTimer.stop()
                mode = "break"
                showNotification()
            }
        }
        countdownTimerBreak = object : CountDownTimer(5000, 1000) { // 20000 milliseconds = 20 seconds
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                // Stop the Chronometer when the countdown timer finishes
                viewTimer.stop()
                mode = "work"
                showNotification()
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