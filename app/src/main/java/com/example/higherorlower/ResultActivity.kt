package com.example.higherorlower

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var scoreTextView: TextView
    lateinit var highScoreTextView: TextView
    lateinit var highScoreSubHeadingTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        scoreTextView = findViewById(R.id.scoreTextView2)
        highScoreTextView = findViewById(R.id.highScoreTextView2)
        highScoreSubHeadingTextView = findViewById(R.id.highScoreSubHeadingTextView)
        val menuButton = findViewById<Button>(R.id.menuButton)


        val score = intent.getIntExtra("point", 0)
        val checkNewHighScore = intent.getBooleanExtra("checkHighScore", false)
        val highScore = intent.getIntExtra("highScore", 0)

        viewResult(score, highScore)
        if (checkNewHighScore) {
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
        highScoreTextView.text = getString(R.string.resultScore_textview, highScore)
    }
}
