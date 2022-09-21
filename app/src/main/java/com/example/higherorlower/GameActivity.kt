package com.example.higherorlower

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.*
import java.lang.Runnable

class GameActivity : AppCompatActivity() {
    lateinit var imageViewOldCard: ImageView
    lateinit var imageViewSecretCard: ImageView
    lateinit var pointTextView: TextView
    var image = R.drawable.heartace
    var oldCardValue = 1 // Första bild är alltid ess.
    var secretCardValue = 0
    var point = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        pointTextView = findViewById(R.id.pointTextView)
        imageViewOldCard = findViewById(R.id.imageViewOldCard)
        imageViewSecretCard = findViewById(R.id.imageViewSecretCard)
        val lowerButton = findViewById<Button>(R.id.lowerButton)
        val higherButton = findViewById<Button>(R.id.higherButton)

        higherButton.setOnClickListener {
            handleHigherButtonPress()
        }
        lowerButton.setOnClickListener {
            handleLowerButtonPress()

        }
    }
    fun handleHigherButtonPress () {
        showSecretCard()
        checkGuessHigher()
        changeCards()
    }

    fun handleLowerButtonPress () {
        showSecretCard()
        checkGuessLower()
        changeCards()
    }

    fun showSecretCard () {
        val randomNumber = (1..52).random()
        when (randomNumber) {
            1 -> {
                image = R.drawable.heartace
                secretCardValue = 1
            }
            2 -> {
                image = R.drawable.heart2
                secretCardValue = 2
            }
            3 -> {
                image = R.drawable.heart3
                secretCardValue = 3
            }
            4 -> {
                image = R.drawable.heart4
                secretCardValue = 4
            }
            5 -> {
                image = R.drawable.heart5
                secretCardValue = 5
            }
            6 -> {
                image = R.drawable.heart6
                secretCardValue = 6
            }
            7 -> {
                image = R.drawable.heart7
                secretCardValue = 7
            }
            8 -> {
                image = R.drawable.heart8
                secretCardValue = 8
            }
            9 -> {
                image = R.drawable.heart9
                secretCardValue = 9
            }
            10 -> {
                image = R.drawable.heart10
                secretCardValue = 10
            }
            11 -> {
                image = R.drawable.heartj
                secretCardValue = 11
            }
            12 -> {
                image = R.drawable.heartq
                secretCardValue = 12
            }
            13 -> {
                image = R.drawable.heartk
                secretCardValue = 13
            }
            14 -> {
                image = R.drawable.clubace
                secretCardValue = 1
            }
            15 -> {
                image = R.drawable.club2
                secretCardValue = 2
            }
            16 -> {
                image = R.drawable.club3
                secretCardValue = 3
            }
            17 -> {
                image = R.drawable.club4
                secretCardValue = 4
            }
            18 -> {
                image = R.drawable.club5
                secretCardValue = 5
            }
            19 -> {
                image = R.drawable.club6
                secretCardValue = 6
            }
            20 -> {
                image = R.drawable.club7
                secretCardValue = 7
            }
            21 -> {
                image = R.drawable.club8
                secretCardValue = 8
            }
            22 -> {
                image = R.drawable.club9
                secretCardValue = 9
            }
            23 -> {
                image = R.drawable.club10
                secretCardValue = 10
            }
            24 -> {
                image = R.drawable.clubj
                secretCardValue = 11
            }
            25 -> {
                image = R.drawable.clubq
                secretCardValue = 12
            }
            26 -> {
                image = R.drawable.clubk
                secretCardValue = 13
            }
            27 -> {
                image = R.drawable.diamondsace
                secretCardValue = 1
            }
            28 -> {
                image = R.drawable.diamonds2
                secretCardValue = 2
            }
            29 -> {
                image = R.drawable.diamonds3
                secretCardValue = 3
            }
            30 -> {
                image = R.drawable.diamonds4
                secretCardValue = 4
            }
            31 -> {
                image = R.drawable.diamonds5
                secretCardValue = 5
            }
            32 -> {
                image = R.drawable.diamonds6
                secretCardValue = 6
            }
            33 -> {
                image = R.drawable.diamonds7
                secretCardValue = 7
            }
            34 -> {
                image = R.drawable.diamonds8
                secretCardValue = 8
            }
            35 -> {
                image = R.drawable.diamonds9
                secretCardValue = 9
            }
            36 -> {
                image = R.drawable.diamonds10
                secretCardValue = 10
            }
            37 -> {
                image = R.drawable.diamondsj
                secretCardValue = 11
            }
            38 -> {
                image = R.drawable.diamondsq
                secretCardValue = 12
            }
            39 -> {
                image = R.drawable.diamondsk
                secretCardValue = 13
            }
            40 -> {
                image = R.drawable.spadesace
                secretCardValue = 1
            }
            41 -> {
                image = R.drawable.spades2
                secretCardValue = 2
            }
            42 -> {
                image = R.drawable.spades3
                secretCardValue = 3
            }
            43 -> {
                image = R.drawable.spades4
                secretCardValue = 4
            }
            44 -> {
                image = R.drawable.spades5
                secretCardValue = 5
            }
            45 -> {
                image = R.drawable.spades6
                secretCardValue = 6
            }
            46 -> {
                image = R.drawable.spades7
                secretCardValue = 7
            }
            47 -> {
                image = R.drawable.spades8
                secretCardValue = 8
            }
            48 -> {
                image = R.drawable.spades9
                secretCardValue = 9
            }
            49 -> {
                image = R.drawable.spades10
                secretCardValue = 10
            }
            50 -> {
                image = R.drawable.spadesj
                secretCardValue = 11
            }
            51 -> {
                image = R.drawable.spadesq
                secretCardValue = 12
            }
            52 -> {
                image = R.drawable.spadesk
                secretCardValue = 13
            }
        }
        imageViewSecretCard.setImageResource(image)
    }

    fun changeCards () {
        Handler(Looper.getMainLooper()).postDelayed( {
            imageViewSecretCard.setImageResource(R.drawable.cardback)
            imageViewOldCard.setImageResource(image)
            oldCardValue = secretCardValue
        }, 3000)
    }

    fun checkGuessHigher () {
        if (secretCardValue > oldCardValue) {
            point++
            pointTextView.text = "Poäng: $point"
        }
        else {
            Log.d("!!!", "Fel gissning")
        }
    }

    fun checkGuessLower () {
        if (secretCardValue < oldCardValue) {
            point++
            pointTextView.text = "Poäng: $point"
        }
        else {
            Log.d("!!!", "Fel gissning")
        }
    }

//    fun increaseScore () {
//        val guessLower = checkGuessLower()
//        val guessHigher = checkGuessHigher()
//        if (guessLower || guessHigher) {
//            point++
//            pointTextView.text = ("Poäng: $point")
//        }
//    }
}

