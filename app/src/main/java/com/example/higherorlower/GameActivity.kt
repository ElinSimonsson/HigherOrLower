package com.example.higherorlower

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    lateinit var imageViewFrontCard: ImageView
    lateinit var imageViewBackCard: ImageView
    lateinit var heartImageView1: ImageView
    lateinit var heartImageView2: ImageView
    lateinit var heartImageView3: ImageView
    lateinit var scoreTextView: TextView
    lateinit var highScoreTextView: TextView
    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    lateinit var roundedCurrentCard: Bitmap
    lateinit var roundedPreviousCard: Bitmap
    lateinit var roundedBackCard: Bitmap
    val sharedPrefFile = "kotlinsharedpreference"
    var currentHighScore = 0
    var checkHighScore = false
    var difficulty: String? = null
    var previousCard: Card? = null
    var currentCard: Card? = null
    var life = 0
    var score = 0
    val deck = Deck()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        layout = findViewById(R.id.layout)
        scoreTextView = findViewById(R.id.scoreTextView1)
        highScoreTextView = findViewById(R.id.highScoreTextView1)
        imageViewFrontCard = findViewById(R.id.imageViewFrontCard)
        imageViewBackCard = findViewById(R.id.imageViewBackCard)
        imageViewPreviousCard = findViewById(R.id.imageViewOldCard)
        heartImageView1 = findViewById(R.id.heartImageView1)
        heartImageView2 = findViewById(R.id.heartImageView2)
        heartImageView3 = findViewById(R.id.heartImageView3)
        higherButton = findViewById(R.id.higherButton)
        lowerButton = findViewById(R.id.lowerButton)


        initializeLayoutGame()

        lowerButton.setOnClickListener {
            handleLowerButtonPress()

        }
        higherButton.setOnClickListener {
            handleHigherButtonPress()
        }
        val imageView = findViewById<ImageView>(R.id.backImageView).setOnClickListener {
            finish()
        }
    }

    override fun onRestart() {
        super.onRestart()
        score = 0
        scoreTextView.text = getString(R.string.point_textview, 0)
    }

    fun handleHigherButtonPress() {
        randomCurrentCard()
        val checkGuess = checkGuessHigher()
        checkCorrectGuess(checkGuess)
        moveCurrentCardToPrevious()
    }

    fun handleLowerButtonPress() {
        randomCurrentCard()
        val checkGuess = checkGuessLower()
        checkCorrectGuess(checkGuess)
        moveCurrentCardToPrevious()
    }

    fun initializeLayoutGame() {
        difficulty = getDifficultyUser()
        showBackCard()
        life = createLive(difficulty!!)
        createHearts(life)
        scoreTextView.text = getString(R.string.point_textview, score)
        val previousHighScore = getPreviousSharedHighScore()
        highScoreTextView.text = getString(R.string.highScore_textview, previousHighScore)
        randomPreviousCard()
    }

    fun getCurrentSharedHighScore(): Int {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val sharedHighScore = sharedPreferences.getInt("highScore_key", 0)
        if (score > sharedHighScore) {
            checkHighScore = true
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("highScore_key", score)
            editor.apply()
            val newHighScore = sharedPreferences.getInt("highScore_key", 0)
            return newHighScore
        }
        return sharedHighScore
    }

    fun getPreviousSharedHighScore(): Int {
        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val previousSharedHighScore = sharedPreferences.getInt("highScore_key", 0)
        return previousSharedHighScore
    }

    fun roundedImage(image: Int): Bitmap {
        val scale = applicationContext.resources.displayMetrics.density
        imageViewFrontCard.cameraDistance = 8000 * scale
        imageViewBackCard.cameraDistance = 8000 * scale
        val bitmap = (AppCompatResources.getDrawable(this, image) as BitmapDrawable).bitmap
        val imageRounded = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(imageRounded)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        canvas.drawRoundRect(
            RectF(5F, 5F, bitmap.width.toFloat(), bitmap.height.toFloat()),
            30F, 30F, paint
        )
        return imageRounded
    }

    fun randomPreviousCard() {
        previousCard = deck.randomPreviousCard()
        roundedPreviousCard = roundedImage(previousCard!!.image)
        imageViewPreviousCard.setImageBitmap(roundedPreviousCard)
    }

    fun randomCurrentCard() {
        imageViewFrontCard.visibility = View.VISIBLE
        currentCard = deck.randomNewCard(previousCard!!)
        flapToFrontCard()
    }

    fun showBackCard() {
        roundedBackCard = roundedImage(R.drawable.cardback)
        imageViewBackCard.setImageBitmap(roundedBackCard)
    }

    fun moveCurrentCardToPrevious() {
        higherButton.setOnClickListener(null)
        lowerButton.setOnClickListener(null)

        Handler(Looper.getMainLooper()).postDelayed({
            flapToBackCard()

            Handler(Looper.getMainLooper()).postDelayed({
                previousCard = currentCard
                roundedPreviousCard = roundedImage(previousCard!!.image)
                imageViewPreviousCard.setImageBitmap(roundedPreviousCard)
            }, 100)

            higherButton.setOnClickListener { handleHigherButtonPress() }
            lowerButton.setOnClickListener { handleLowerButtonPress() }
        }, 2000)

    }

    fun checkCorrectGuess(correctGuess: Boolean) {
        if (correctGuess) {
            score++
            scoreTextView.text = getString(R.string.point_textview, score)
        } else {
            reduceLife()
            if (life < 1) {
                currentHighScore = getCurrentSharedHighScore()
                val intent = Intent(this, ResultActivity::class.java)
                Handler(Looper.getMainLooper()).postDelayed({
                    intent.putExtra("point", score)
                    intent.putExtra("highScore", currentHighScore)
                    intent.putExtra("checkHighScore", checkHighScore)
                    startActivity(intent)
                    finish() // direkt till main från resultActivity
                }, 1500)
            }
        }
    }

    fun reduceLife() {
        if (life > 1) {
            life--
            when (life) {
                2 -> {
                    heartImageView3.blink(8)
                    heartImageView3.visibility = View.INVISIBLE
                }
                1 -> {
                    heartImageView2.blink(8)
                    heartImageView2.visibility = View.INVISIBLE
                }
            }
        } else {
            life--
            heartImageView1.blink(8)
            heartImageView1.visibility = View.INVISIBLE
        }
    }

    fun checkGuessHigher(): Boolean {
        return currentCard!!.value > previousCard!!.value
    }

    fun checkGuessLower(): Boolean {
        return currentCard!!.value < previousCard!!.value
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

    fun flapToBackCard() {
        roundedBackCard = roundedImage(R.drawable.cardback)
        imageViewBackCard.setImageBitmap(roundedBackCard)
        front_anim = AnimatorInflater.loadAnimator(
            applicationContext,
            R.animator.front_animator
        ) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(
            applicationContext,
            R.animator.back_animator
        ) as AnimatorSet
        front_anim.setTarget(imageViewFrontCard)
        back_anim.setTarget(imageViewBackCard)
        front_anim.start()
        back_anim.start()
    }

    fun flapToFrontCard() {
        roundedCurrentCard = roundedImage(currentCard!!.image)
        imageViewFrontCard.setImageBitmap(roundedCurrentCard)
        front_anim = AnimatorInflater.loadAnimator(
            applicationContext,
            R.animator.front_animator
        ) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(
            applicationContext,
            R.animator.back_animator
        ) as AnimatorSet
        front_anim.setTarget(imageViewBackCard)
        back_anim.setTarget(imageViewFrontCard)
        front_anim.start()
        back_anim.start()
    }
}

