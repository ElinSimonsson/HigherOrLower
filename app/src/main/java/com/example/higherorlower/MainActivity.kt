package com.example.higherorlower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }
}