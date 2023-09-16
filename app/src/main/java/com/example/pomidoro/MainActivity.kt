package com.example.pomidoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
class MainActivity : AppCompatActivity() {
    private lateinit var timer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timer = findViewById(R.id.text_view_timer)
    }
}