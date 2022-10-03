package com.example.higherorlower

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Property
import android.view.ContextThemeWrapper
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext, "Ok", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        easyButton = findViewById(R.id.easyButton)
        mediumButton = findViewById(R.id.mediumButton)
        hardButton = findViewById(R.id.hardButton)
        val playButton = findViewById<Button>(R.id.playButton)
        val questionMarkView = findViewById<ImageView>(R.id.questionMarkImageView)
        errorTextView = findViewById(R.id.errorTextView)
        heartImageViewFirst = findViewById(R.id.heartImageViewFirst)
        heartImageViewSecond = findViewById(R.id.heartImageViewSecond)
        heartImageViewThird = findViewById(R.id.heartImageViewThird)

        setWhiteButtons()

        easyButton.setOnClickListener{
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
            instructionsAlertDialog(view = it)
        }
    }

    override fun onRestart() {
        super.onRestart()
        difficulty = " "
        errorTextView.visibility = View.INVISIBLE
        setWhiteButtons()
        setHeartsInvisible()
    }

    fun instructionsAlertDialog (view: View) {
        val alertDialog = android.app.AlertDialog.Builder(ContextThemeWrapper(this, R.style.AlertDialogCustom)).create()

        alertDialog.setTitle(getString(R.string.alertDialogTitle_textview))
        alertDialog.setMessage(getString(R.string.instructions_textview))
        alertDialog.setButton(
            android.app.AlertDialog.BUTTON_POSITIVE, "Ok"
        ) { dialog, which -> dialog.dismiss() }

        alertDialog.show()
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

    fun handleEasyButtonPress() {
        difficulty = "Easy"
        heartImageViewFirst.visibility = View.VISIBLE
        heartImageViewSecond.visibility = View.VISIBLE
        heartImageViewThird.visibility = View.VISIBLE
        setEasyButtonToRed()
        setWhiteMediumButton()
        setWhiteHardButton()
    }
    fun handleMediumButtonPress() {
        difficulty = "Medium"
        heartImageViewFirst.visibility = View.VISIBLE
        heartImageViewSecond.visibility = View.VISIBLE
        heartImageViewThird.visibility = View.INVISIBLE
        setWhiteEasyButton()
        setMediumButtonToRed()
        setWhiteHardButton()

    }
    fun handleHardButtonPress() {
        difficulty = "Hard"
        heartImageViewFirst.visibility = View.VISIBLE
        heartImageViewSecond.visibility = View.INVISIBLE
        heartImageViewThird.visibility = View.INVISIBLE
        setWhiteEasyButton()
        setWhiteMediumButton()
        setHardButtonToRed()
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

    fun setEasyButtonToRed () {
        easyButton.setBackgroundColor(Color.parseColor("#D50000"))
        easyButton.setTextColor(Color.WHITE)
    }
    fun setMediumButtonToRed () {
        mediumButton.setBackgroundColor(Color.parseColor("#D50000"))
        mediumButton.setTextColor(Color.WHITE)
    }
    fun setHardButtonToRed () {
        hardButton.setBackgroundColor(Color.parseColor("#D50000"))
        hardButton.setTextColor(Color.WHITE)
    }
    fun setWhiteEasyButton () {
        easyButton.setTextColor(Color.parseColor("#D50000"))
        easyButton.setBackgroundColor(Color.WHITE)
    }
    fun setWhiteMediumButton () {
        mediumButton.setTextColor(Color.parseColor("#D50000"))
        mediumButton.setBackgroundColor(Color.WHITE)
    }
    fun setWhiteHardButton () {
        hardButton.setTextColor(Color.parseColor("#D50000"))
        hardButton.setBackgroundColor(Color.WHITE)
    }
    fun setWhiteButtons() {
        easyButton.setTextColor(Color.parseColor("#D50000"))
        easyButton.setBackgroundColor(Color.WHITE)
        mediumButton.setTextColor(Color.parseColor("#D50000"))
        mediumButton.setBackgroundColor(Color.WHITE)
        hardButton.setTextColor(Color.parseColor("#D50000"))
        hardButton.setBackgroundColor(Color.WHITE)
    }
    fun setHeartsInvisible () {
        heartImageViewFirst.visibility = View.INVISIBLE
        heartImageViewSecond.visibility = View.INVISIBLE
        heartImageViewThird.visibility = View.INVISIBLE
    }
}
