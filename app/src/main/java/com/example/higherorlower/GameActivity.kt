package com.example.higherorlower

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    var point = 0
    var highscore = 0
    var deck = mutableListOf<Card>()
    var previousCard: Card? = null
    var currentCard: Card? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        layout = findViewById(R.id.layout)
        pointTextView = findViewById(R.id.pointTextView)
        imageViewCurrentCard = findViewById(R.id.imageViewSecretCard)
        imageViewPreviousCard = findViewById(R.id.imageViewOldCard)
        val lowerButton = findViewById<Button>(R.id.lowerButton)
        val higherButton = findViewById<Button>(R.id.higherButton)
        layout.setBackgroundResource(R.drawable.greenbackground)
        createDeck()

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

    fun createDeck() {
        val card1 = Card(R.drawable.heartace, 1)
        val card2 = Card(R.drawable.heart2, 2)
        val card3 = Card(R.drawable.heart3, 3)
        val card4 = Card(R.drawable.heart4, 4)
        val card5 = Card(R.drawable.heart5, 5)
        val card6 = Card(R.drawable.heart6, 6)
        val card7 = Card(R.drawable.heart7, 7)
        val card8 = Card(R.drawable.heart8, 8)
        val card9 = Card(R.drawable.heart9, 9)
        val card10 = Card(R.drawable.heart10, 10)
        val card11 = Card(R.drawable.heartj, 11)
        val card12 = Card(R.drawable.heartq, 12)
        val card13 = Card(R.drawable.heartk, 13)
        val card14 = Card(R.drawable.clubace, 1)
        val card15 = Card(R.drawable.club2, 2)
        val card16 = Card(R.drawable.club3, 3)
        val card17 = Card(R.drawable.club4, 4)
        val card18 = Card(R.drawable.club5, 5)
        val card19 = Card(R.drawable.club6, 6)
        val card20 = Card(R.drawable.club7, 7)
        val card21 = Card(R.drawable.club8, 8)
        val card22 = Card(R.drawable.club9, 9)
        val card23 = Card(R.drawable.club10, 10)
        val card24 = Card(R.drawable.clubj, 11)
        val card25 = Card(R.drawable.clubq, 12)
        val card26 = Card(R.drawable.clubk, 13)
        val card27 = Card(R.drawable.spadesace, 1)
        val card28 = Card(R.drawable.spades2, 2)
        val card29 = Card(R.drawable.spades3, 3)
        val card30 = Card(R.drawable.spades4, 4)
        val card31 = Card(R.drawable.spades5, 5)
        val card32 = Card(R.drawable.spades6, 6)
        val card33 = Card(R.drawable.spades7, 7)
        val card34 = Card(R.drawable.spades8, 8)
        val card35 = Card(R.drawable.spades9, 9)
        val card36 = Card(R.drawable.spades10, 10)
        val card37 = Card(R.drawable.spadesj, 11)
        val card38 = Card(R.drawable.spadesq, 12)
        val card39 = Card(R.drawable.spadesk, 13)
        val card40 = Card(R.drawable.diamondsace, 1)
        val card41 = Card(R.drawable.diamonds2, 2)
        val card42 = Card(R.drawable.diamonds3, 3)
        val card43 = Card(R.drawable.diamonds4, 4)
        val card44 = Card(R.drawable.diamonds5, 5)
        val card45 = Card(R.drawable.diamonds6, 6)
        val card46 = Card(R.drawable.diamonds7, 7)
        val card47 = Card(R.drawable.diamonds8, 8)
        val card48 = Card(R.drawable.diamonds9, 9)
        val card49 = Card(R.drawable.diamonds10, 10)
        val card50 = Card(R.drawable.diamondsj, 11)
        val card51 = Card(R.drawable.diamondsq, 12)
        val card52 = Card(R.drawable.diamondsk, 13)
        deck.add(card1)
        deck.add(card2)
        deck.add(card3)
        deck.add(card4)
        deck.add(card5)
        deck.add(card6)
        deck.add(card7)
        deck.add(card8)
        deck.add(card9)
        deck.add(card10)
        deck.add(card11)
        deck.add(card12)
        deck.add(card13)
        deck.add(card14)
        deck.add(card15)
        deck.add(card16)
        deck.add(card17)
        deck.add(card18)
        deck.add(card19)
        deck.add(card20)
        deck.add(card21)
        deck.add(card22)
        deck.add(card23)
        deck.add(card24)
        deck.add(card25)
        deck.add(card26)
        deck.add(card27)
        deck.add(card28)
        deck.add(card29)
        deck.add(card30)
        deck.add(card31)
        deck.add(card32)
        deck.add(card33)
        deck.add(card34)
        deck.add(card35)
        deck.add(card36)
        deck.add(card37)
        deck.add(card38)
        deck.add(card39)
        deck.add(card40)
        deck.add(card41)
        deck.add(card42)
        deck.add(card43)
        deck.add(card44)
        deck.add(card45)
        deck.add(card46)
        deck.add(card47)
        deck.add(card48)
        deck.add(card49)
        deck.add(card50)
        deck.add(card51)
        deck.add(card52)
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
        deck.shuffle()
        previousCard = deck[10]
        imageViewPreviousCard.setImageResource(previousCard!!.image)
    }

    fun randomCurrentCard() {
        deck.shuffle()
        currentCard = deck[20]
        while (currentCard?.value == previousCard?.value) {
            deck.shuffle()
            currentCard = deck[25]
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
        layout.setBackgroundColor(Color.RED)
        highscore = point
        Handler(Looper.getMainLooper()).postDelayed({
            intent.putExtra("highScore", highscore)
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
            gameOver()
        }
    }
}

