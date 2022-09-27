package com.example.higherorlower

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isInvisible

class ResultActivity : AppCompatActivity() {
    lateinit var scoreTextView: TextView
    lateinit var highScoreTextView: TextView
    lateinit var highScoreSubHeadingTextView: TextView
    val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        scoreTextView = findViewById(R.id.scoreTextView)
        highScoreTextView = findViewById(R.id.highScoreTextView)
        highScoreSubHeadingTextView = findViewById(R.id.highScoreSubHeadingTextView)
        val menuButton = findViewById<Button>(R.id.menuButton)


        val score = getScore()

        val previousSharedHighScore = getPreviousSharedHighScore()
        val currentHighScore = sharedHighScore(score)

        viewResult(score, currentHighScore)
        if (currentHighScore > previousSharedHighScore) {
            highScoreSubHeadingTextView.blink()
        } else {
            highScoreSubHeadingTextView.alpha = 0.0f
        }
        menuButton.setOnClickListener {
            finish()
        }
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

    fun viewResult(score: Int, highScore: Int) {
        scoreTextView.text = getString(R.string.resultScore_textview, score)
        highScoreTextView.text = getString(R.string.highScore_textview, highScore)
    }

    fun getScore(): Int {
        val point = intent.getIntExtra("point", 0)
        return point
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
