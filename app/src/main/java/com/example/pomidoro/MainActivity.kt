package com.example.pomidoro

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import kotlin.properties.Delegates

const val CHANNEL_ID = "my_notification_channel"
class MainActivity : AppCompatActivity() {
    private lateinit var buttonStart: Button
    private lateinit var viewTimer: Chronometer
    private var countdownTimer: CountDownTimer? = null
    private lateinit var durationRadioGroup: RadioGroup
    private lateinit var textViewChoice: TextView
    private var millisInFuture by Delegates.notNull<Long>()

//    val fullScreenIntent = Intent(this, CallActivity::class.java)
//    val fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
//        fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)

    val alarmBuilder =
        NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle("Time for a break")
            .setContentText("you have 10 mins of break")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_CALL)

            // Use a full-screen intent only for the highest-priority alerts where you
            // have an associated activity that you would like to launch after the user
            // interacts with the notification. Also, if your app targets Android 10
            // or higher, you need to request the USE_FULL_SCREEN_INTENT permission in
            // order for the platform to invoke this notification.
//            .setFullScreenIntent(fullScreenPendingIntent, true)

    val alarm = alarmBuilder.build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonStart = findViewById(R.id.button_start)
        viewTimer = findViewById(R.id.view_timer)
        viewTimer.isCountDown = true
        durationRadioGroup = findViewById(R.id.duration_radio_group)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "My Notification Channel"
            val channelDescription = "This is a notification channel for my app"
            val importance = NotificationManager.IMPORTANCE_HIGH // Choose an importance level

            val channel = NotificationChannel(CHANNEL_ID, channelName, importance).apply {
                description = channelDescription
                // You can customize other channel settings here, such as sound and vibration
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        countdownTimer = object : CountDownTimer(millisInFuture, 1000) { // 20000 milliseconds = 20 seconds
            override fun onTick(millisUntilFinished: Long) {
                // Update UI or perform actions on each tick if needed
            }

            override fun onFinish() {
                // Stop the Chronometer when the countdown timer finishes
                viewTimer.stop()
                alarm
            }
        }

        buttonStart.setOnClickListener {
            when (durationRadioGroup.checkedRadioButtonId) {
                R.id.radioOption1 -> {
                    viewTimer.base =
                        SystemClock.elapsedRealtime() + (1 * 60 * 1000)
                    millisInFuture = (1 * 60 * 1000)
                } // 30 minutes in milliseconds
                R.id.radioOption2 -> {
                        viewTimer.base =
                        SystemClock.elapsedRealtime() + (35 * 60 * 1000)
                    millisInFuture = (35 * 60 * 1000)
                } // 35 minutes in milliseconds
                R.id.radioOption3 -> {
                    viewTimer.base =
                        SystemClock.elapsedRealtime() + (45 * 60 * 1000)
                    millisInFuture = (45 * 60 * 1000)
                } // 40 minutes in milliseconds
                else -> Toast.makeText(this, "Please select a duration.", Toast.LENGTH_SHORT).show()
            }
            countdownTimer?.start()
            viewTimer.start()
            hideOptions()
        }
    }

    private fun hideOptions() {
        textViewChoice = findViewById(R.id.text_view_choice)
        textViewChoice.visibility = View.GONE
        durationRadioGroup.visibility = View.GONE
        viewTimer.visibility = View.VISIBLE
    }
}

