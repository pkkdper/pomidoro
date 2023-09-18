package com.example.pomidoro

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Handle the alarm action here, such as showing a notification or playing a sound
        // You can use NotificationManager or MediaPlayer for this purpose
    }
}