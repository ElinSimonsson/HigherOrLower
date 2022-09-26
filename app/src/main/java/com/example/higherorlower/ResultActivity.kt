package com.example.higherorlower

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var scoreTextView: TextView
    lateinit var highScoreTextView: TextView
    lateinit var highScoreBrokenTextView: TextView
    val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        scoreTextView = findViewById(R.id.scoreTextView)
        highScoreTextView = findViewById(R.id.highScoreTextView)
        highScoreBrokenTextView = findViewById(R.id.highScoreBroken)
        val backImageButton = findViewById<ImageButton>(R.id.backImageButton)

        val point = getPoint()
        val previousActivityHighScore = getPreviousHighscore()
        val previousSharedHighScore = getPreviousSharedHighScore()
        val currentHighScore = sharedHighScore(previousActivityHighScore)

        viewResult(point, currentHighScore)
        if (currentHighScore > previousSharedHighScore) {
            highScoreBrokenTextView.text =
                getString(R.string.highScoreIsBroken_textView, "NEW HIGH SCORE!")
        } else {
            highScoreBrokenTextView.text = getString(R.string.highScoreIsBroken_textView, " ")
        }
        backImageButton.setOnClickListener {
            finish()
        }
    }

    fun viewResult(score: Int, highScore: Int) {
        scoreTextView.text = getString(R.string.resultScore_textview, score)
        highScoreTextView.text = getString(R.string.highscore_textview, highScore)
    }

    fun getPoint(): Int {
        val point = intent.getIntExtra("point", 0)
        return point
    }

    fun getPreviousHighscore(): Int {
        val previousHighScore = intent.getIntExtra("sharedHighScore", 0)
        return previousHighScore
    }

    fun sharedHighScore(highScorePreviousActivity: Int): Int {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharedHighScore = sharedPreferences.getInt("previousHighScore_key", 0)
        if (highScorePreviousActivity > sharedHighScore) {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("previousHighScore_key", highScorePreviousActivity)
            editor.apply()
            val newHighScore = sharedPreferences.getInt("previousHighScore_key", 0)
            return newHighScore
        }
        return sharedHighScore
    }

    fun getPreviousSharedHighScore(): Int {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val previousSharedHighScore = sharedPreferences.getInt("previousHighScore_key", 0)
        return previousSharedHighScore
    }
}
