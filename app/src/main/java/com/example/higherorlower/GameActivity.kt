package com.example.higherorlower

import Deck
import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout

class GameActivity : AppCompatActivity() {
    lateinit var layout: ConstraintLayout
    lateinit var lowerButton: Button
    lateinit var higherButton: Button
    lateinit var imageViewPreviousCard: ImageView
    lateinit var imageViewCurrentCard: ImageView
    lateinit var heartImageView1: ImageView
    lateinit var heartImageView2: ImageView
    lateinit var heartImageView3: ImageView
    lateinit var pointTextView: TextView
    var difficulty: String? = null
    var life = 0
    var point = 0
    var previousCard: Card? = null
    var currentCard: Card? = null
    lateinit var roundedImage: Bitmap
    val deck = Deck()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        layout = findViewById(R.id.layout)
        pointTextView = findViewById(R.id.pointTextView)
        // lifeLeftTextView = findViewById(R.id.lifeTextView)
        imageViewCurrentCard = findViewById(R.id.imageViewSecretCard)
        imageViewPreviousCard = findViewById(R.id.imageViewOldCard)
        heartImageView1 = findViewById(R.id.heartImageView1)
        heartImageView2 = findViewById(R.id.heartImageView2)
        heartImageView3 = findViewById(R.id.heartImageView3)
        lowerButton = findViewById(R.id.lowerButton)
        higherButton = findViewById(R.id.higherButton)
        layout.setBackgroundResource(R.drawable.greenbackground)
        difficulty = getDifficultyUser()

        showBackCard()
        life = createLive(difficulty!!)
        createHearts(life)
        Log.d("!!!", "Antal liv: $life")

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
    fun roundedImage (image: Int): Bitmap {
        val bitmap = (AppCompatResources.getDrawable(this,image) as BitmapDrawable).bitmap
        val imageRounded = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(imageRounded)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        canvas.drawRoundRect(
            RectF(5F, 5F, bitmap.width.toFloat(), bitmap.height.toFloat()),
            30F, 30F, paint)
        return imageRounded
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
        roundedImage = roundedImage(previousCard!!.image)
        imageViewPreviousCard.setImageResource(previousCard!!.image)
        imageViewPreviousCard.setImageBitmap(roundedImage)
    }

    fun randomCurrentCard() {
        var shuffleAgain = true
        while (shuffleAgain) {
            currentCard = deck.randomNewCard()

            if (currentCard?.value != previousCard?.value) {
                if (deck.checkUsedDeck(currentCard!!)) {
                    shuffleAgain = false
                }
            }
            roundedImage = roundedImage(currentCard!!.image)
            imageViewCurrentCard.setImageResource(currentCard!!.image)
            imageViewCurrentCard.setImageBitmap(roundedImage)
        }
    }

    fun showBackCard () {
        roundedImage = roundedImage(R.drawable.cardback)
        imageViewCurrentCard.setImageBitmap(roundedImage)
    }

    fun changeCurrentCardToPrevious() {
        higherButton.setOnClickListener(null)
        lowerButton.setOnClickListener(null)
        Handler(Looper.getMainLooper()).postDelayed({

            var roundedBackCardAsRounded = roundedImage(R.drawable.cardback)
            imageViewCurrentCard.setImageBitmap(roundedBackCardAsRounded)

            previousCard = currentCard
            roundedImage = roundedImage(previousCard!!.image)
            //imageViewPreviousCard.setImageResource(previousCard!!.image)
            imageViewPreviousCard.setImageBitmap(roundedImage)
            higherButton.setOnClickListener(View.OnClickListener { handleHigherButtonPress()})
            lowerButton.setOnClickListener(View.OnClickListener { handleLowerButtonPress() })
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
            point++
            pointTextView.text = getString(R.string.point_textview, point)
        } else {
            wrongGuess()
        }
    }

    fun wrongGuess() {
        if (life > 1) {
            life--
            when (life) {
                2 -> {
                    heartImageView3.blink(5)
                    heartImageView3.visibility = View.INVISIBLE
                }
                1 -> {
                    heartImageView2.blink(5)
                    heartImageView2.visibility = View.INVISIBLE
                }
            }
        } else {
            life--
            heartImageView1.blink(5)
            heartImageView1.visibility = View.INVISIBLE
            gameOver()
        }
    }

    fun getDifficultyUser(): String? {
        val answerDifficulty = intent.getStringExtra("difficulty")
        return answerDifficulty
    }

    fun createLive(difficulty: String): Int {
        when (difficulty) {
            "Easy" -> life = 3
            "Medium" -> life = 2
            "Hard" -> life = 1
        }
        return life
    }

    fun createHearts(lives: Int) {
        when (lives) {
            1 -> heartImageView1.visibility = View.VISIBLE
            2 -> {
                heartImageView1.visibility = View.VISIBLE
                heartImageView2.visibility = View.VISIBLE
            }
            3 -> {
                heartImageView1.visibility = View.VISIBLE
                heartImageView2.visibility = View.VISIBLE
                heartImageView3.visibility = View.VISIBLE
            }
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
}

