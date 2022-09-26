package com.example.higherorlower

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        val questionMark = findViewById<ImageButton>(R.id.questionMarkImageButton)

        playButton.setOnClickListener {
            handlePlayButtonPress()
        }
        questionMark.setOnClickListener {
            handleQuestionImageButtonPress()
        }
    }

    fun handlePlayButtonPress() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun handleQuestionImageButtonPress () {
        val intent = Intent(this, RulesActivity::class.java)
        startActivity(intent)
    }
}
