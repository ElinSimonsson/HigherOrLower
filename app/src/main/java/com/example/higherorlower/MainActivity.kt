package com.example.higherorlower


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var errorTextView: TextView
    lateinit var heartImageViewFirst: ImageView
    lateinit var heartImageViewSecond: ImageView
    lateinit var heartImageViewThird: ImageView
    lateinit var easyButton: Button
    lateinit var mediumButton: Button
    lateinit var hardButton: Button
    var difficulty = " "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playButton = findViewById<Button>(R.id.playButton)
        val questionMarkView = findViewById<ImageView>(R.id.questionMarkImageView)
        easyButton = findViewById(R.id.easyButton)
        mediumButton = findViewById(R.id.mediumButton)
        hardButton = findViewById(R.id.hardButton)
        errorTextView = findViewById(R.id.errorTextView)
        heartImageViewFirst = findViewById(R.id.heartImageViewFirst)
        heartImageViewSecond = findViewById(R.id.heartImageViewSecond)
        heartImageViewThird = findViewById(R.id.heartImageViewThird)


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
            instructionsAlertDialog()
        }
    }

    override fun onRestart() {
        super.onRestart()
        difficulty = " "
        errorTextView.visibility = View.INVISIBLE
        setLevelsButtonsToWhite()
        setHeartsInvisible()
    }

    fun instructionsAlertDialog() {
        val alertDialog =
            android.app.AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom))
                .create()
        alertDialog.setTitle(getString(R.string.alertDialogTitle_textview))
        alertDialog.setMessage(getString(R.string.instructions_textview))
        alertDialog.setButton(
            android.app.AlertDialog.BUTTON_POSITIVE, "Ok"
        ) { dialog, which -> dialog.dismiss() }

        alertDialog.show()
    }

    fun handlePlayButtonPress() {
        if (difficulty == " ") {
            errorTextView.visibility = View.VISIBLE
            errorTextView.blink(5)
        } else {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("difficulty", difficulty)
            startActivity(intent)
        }
    }

    fun handleEasyButtonPress() {
        difficulty = "Easy"
        heartImageViewFirst.visibility = View.VISIBLE
        heartImageViewSecond.visibility = View.VISIBLE
        heartImageViewThird.visibility = View.VISIBLE
        setEasyButtonToGreen()
        setMediumButtonToWhite()
        setHardButtonToWhite()
    }

    fun handleMediumButtonPress() {
        difficulty = "Medium"
        heartImageViewFirst.visibility = View.VISIBLE
        heartImageViewSecond.visibility = View.VISIBLE
        heartImageViewThird.visibility = View.INVISIBLE
        setEasyButtonToWhite()
        setMediumButtonToGreen()
        setHardButtonToWhite()

    }

    fun handleHardButtonPress() {
        difficulty = "Hard"
        heartImageViewFirst.visibility = View.VISIBLE
        heartImageViewSecond.visibility = View.INVISIBLE
        heartImageViewThird.visibility = View.INVISIBLE
        setEasyButtonToWhite()
        setMediumButtonToWhite()
        setHardButtonToGreen()
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

    fun setEasyButtonToGreen() {
        easyButton.setBackgroundColor(Color.parseColor("#006329"))
        easyButton.setTextColor(Color.WHITE)
    }

    fun setMediumButtonToGreen() {
        mediumButton.setBackgroundColor(Color.parseColor("#006329"))
        mediumButton.setTextColor(Color.WHITE)
    }

    fun setHardButtonToGreen() {
        hardButton.setBackgroundColor(Color.parseColor("#006329"))
        hardButton.setTextColor(Color.WHITE)
    }

    fun setEasyButtonToWhite() {
        easyButton.setTextColor(Color.parseColor("#006329"))
        easyButton.setBackgroundColor(Color.WHITE)
    }

    fun setMediumButtonToWhite() {
        mediumButton.setTextColor(Color.parseColor("#006329"))
        mediumButton.setBackgroundColor(Color.WHITE)
    }

    fun setHardButtonToWhite() {
        hardButton.setTextColor(Color.parseColor("#006329"))
        hardButton.setBackgroundColor(Color.WHITE)
    }

    fun setLevelsButtonsToWhite() {
        easyButton.setTextColor(Color.parseColor("#006329"))
        easyButton.setBackgroundColor(Color.WHITE)
        mediumButton.setTextColor(Color.parseColor("#006329"))
        mediumButton.setBackgroundColor(Color.WHITE)
        hardButton.setTextColor(Color.parseColor("#006329"))
        hardButton.setBackgroundColor(Color.WHITE)
    }

    fun setHeartsInvisible() {
        heartImageViewFirst.visibility = View.INVISIBLE
        heartImageViewSecond.visibility = View.INVISIBLE
        heartImageViewThird.visibility = View.INVISIBLE
    }
}
