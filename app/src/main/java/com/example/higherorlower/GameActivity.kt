package com.example.higherorlower

import Deck
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class GameActivity : AppCompatActivity() {
    lateinit var layout: ConstraintLayout
    lateinit var imageViewPreviousCard: ImageView
    lateinit var imageViewCurrentCard: ImageView
    lateinit var pointTextView: TextView
    lateinit var lifeLeftTextView: TextView
    var difficulty: String? = null
    var life = 0
    var point = 0
    //var deck = mutableListOf<Card>()
    var previousCard: Card? = null
    var currentCard: Card? = null
    val deck = Deck()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        layout = findViewById(R.id.layout)
        pointTextView = findViewById(R.id.pointTextView)
        lifeLeftTextView = findViewById(R.id.lifeTextView)
        imageViewCurrentCard = findViewById(R.id.imageViewSecretCard)
        imageViewPreviousCard = findViewById(R.id.imageViewOldCard)
        val lowerButton = findViewById<Button>(R.id.lowerButton)
        val higherButton = findViewById<Button>(R.id.higherButton)
        layout.setBackgroundResource(R.drawable.greenbackground)
        difficulty = getDifficultyUser()

        //createDeck()
        createLive(difficulty!!)
        lifeLeftTextView.text = getString(R.string.life_textView, life)

        pointTextView.text = getString(R.string.point_textview, point)
        randomPreviousCard()

        higherButton.setOnClickListener {
            handleHigherButtonPress()
        }
        lowerButton.setOnClickListener {
            handleLowerButtonPress()
        }
        val imageView = findViewById<ImageView>(R.id.backImageView).setOnClickListener {
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        point = 0
        pointTextView.text = getString(R.string.point_textview, 0)
    }

    fun handleHigherButtonPress() {
        randomCurrentCard()
        val checkGuess = checkGuessHigher()
        checkCorrectGuess(checkGuess)
        changeCurrentCardToPrevious()
    }

    fun handleLowerButtonPress() {
        randomCurrentCard()
        val checkGuess = checkGuessLower()
        checkCorrectGuess(checkGuess)
        changeCurrentCardToPrevious()
    }

    fun randomPreviousCard() {
        previousCard = deck.randomPreviousCard()
        imageViewPreviousCard.setImageResource(previousCard!!.image)
    }

    fun randomCurrentCard() {
        currentCard = deck.randomNewCard()
        while (currentCard?.value == previousCard?.value) {
           currentCard = deck.randomNewCard() // blanda igen
        }
        imageViewCurrentCard.setImageResource(currentCard!!.image)
    }

    fun changeCurrentCardToPrevious() {
        Handler(Looper.getMainLooper()).postDelayed({
            imageViewCurrentCard.setImageResource(R.drawable.cardback)
            previousCard = currentCard
            imageViewPreviousCard.setImageResource(previousCard!!.image)
        }, 2000)
    }

    fun checkGuessHigher(): Boolean {
        return currentCard!!.value > previousCard!!.value
    }

    fun checkGuessLower(): Boolean {
        return currentCard!!.value < previousCard!!.value
    }

    fun gameOver() {
        val intent = Intent(this, ResultActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            intent.putExtra("point", point)
            startActivity(intent)
            finish() // direkt till main från resultActivity
        }, 1500)
    }

    fun checkCorrectGuess(correctGuess: Boolean) {
        if (correctGuess) {
            layout.setBackgroundColor(Color.GREEN)
            point++
            pointTextView.text = getString(R.string.point_textview, point)
            Handler(Looper.getMainLooper()).postDelayed({
                layout.setBackgroundResource(R.drawable.greenbackground)
            },2000)

        } else {
            layout.setBackgroundColor(Color.RED)
            if(life > 1) {
                life--
                lifeLeftTextView.text = getString(R.string.life_textView, life)
                Handler(Looper.getMainLooper()).postDelayed({
                    layout.setBackgroundResource(R.drawable.greenbackground)
                }, 2000)
            }
            else {
                life--
                lifeLeftTextView.text = getString(R.string.life_textView, life)
                gameOver()
            }
        }
    }

    fun getDifficultyUser (): String? {
        val answerDifficulty = intent.getStringExtra("difficulty")
        return answerDifficulty
    }

    fun createLive (difficulty: String) {
        when (difficulty) {
            "Easy" -> life = 3
            "Medium" -> life = 2
            "Hard" -> life = 1
        }
    }
}

