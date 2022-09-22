package com.example.higherorlower

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            handleButtonPress()
        }
    }
    fun handleButtonPress() {
        var intent = Intent(this, GameActivity::class.java)

        startActivity(intent)
    }
    