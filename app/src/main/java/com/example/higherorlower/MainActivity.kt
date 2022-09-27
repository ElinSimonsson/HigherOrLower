package com.example.higherorlower

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    var difficulty = " "
    lateinit var messageDifficulty: TextView
    lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val easyButton = findViewById<Button>(R.id.easyButton)
        val mediumButton = findViewById<Button>(R.id.mediumButton)
        val hardButton = findViewById<Button>(R.id.hardButton)
        val playButton = findViewById<Button>(R.id.playButton)
        val questionMarkView = findViewById<ImageView>(R.id.questionMarkImageView)
        errorTextView = findViewById(R.id.errorTextView)
        messageDifficulty = findViewById(R.id.messageTextView)
       // messageDifficulty.visibility = View.GONE

        easyButton.setOnClickListener {
            handleEasyButtonPress()
        }
        mediumButton.setOnClickListener {
            handleMediumButtonPress()
        }
        hardButton.setOnClickListener {
            handleHardButtonPress()
        }

        playButton.setOnClickListener {
            handlePlayButtonPress()
        }
        questionMarkView.setOnClickListener {
            handleQuestionImageButtonPress()
        }
    }

    override fun onRestart() {
        super.onRestart()
        difficulty = " "
        messageDifficulty.visibility = View.INVISIBLE
        errorTextView.visibility = View.INVISIBLE
    }

    fun handlePlayButtonPress() {
        if(difficulty == " ") {
            errorTextView.visibility = View.VISIBLE
            errorTextView.blink(5)
        } else {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("difficulty", difficulty)
            startActivity(intent)
        }
    }

    fun handleQuestionImageButtonPress () {
        val intent = Intent(this, RulesActivity::class.java)
        startActivity(intent)
    }
    fun handleEasyButtonPress() {
        difficulty = "Easy"
        messageDifficulty.text = getString(R.string.messageDifficulty_textView, difficulty)
        messageDifficulty.visibility = View.VISIBLE
    }
    fun handleMediumButtonPress() {
        difficulty = "Medium"
        messageDifficulty.text = getString(R.string.messageDifficulty_textView, difficulty)
        messageDifficulty.visibility = View.VISIBLE
    }
    fun handleHardButtonPress() {
        difficulty = "Hard"
        messageDifficulty.text = getString(R.string.messageDifficulty_textView, difficulty)
        messageDifficulty.visibility = View.VISIBLE
    }
    fun View.blink(
        times: Int = Animation.INFINITE,
        duration: Long = 80L,
        offset: Long = 80L,
        minAlpha: Float = 0.0f,
        maxAlpha: Float = 1.0f,
        repeatMode: Int = Animation.REVERSE
    ) {
        startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
            it.duration = duration
            it.startOffset = offset
            it.repeatMode = repeatMode
            it.repeatCount = times
        })
    }
}
