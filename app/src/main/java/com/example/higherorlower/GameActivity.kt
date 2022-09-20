package com.example.higherorlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.*

class GameActivity : AppCompatActivity() {
    lateinit var imageViewOldCard: ImageView
    lateinit var imageViewSecretCard: ImageView
    var image = R.drawable.heartace
    var value = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        imageViewOldCard = findViewById(R.id.imageViewOldCard)
        imageViewSecretCard = findViewById(R.id.imageViewSecretCard)
        val higherButton = findViewById<Button>(R.id.lowerButton)
        val lowerButton = findViewById<Button>(R.id.higherButton)

        higherButton.setOnClickListener {
            Log.d("!!!", "Du tryckte på högre!")
            handleButtonPress()
        }
        lowerButton.setOnClickListener {
            Log.d("!!!", "Du tryckte på lägre!")
            handleButtonPress()

        }
    }
    
    fun handleButtonPress () {
        changeImages()
    }

    fun changeSecretImage () {
        val randomNumber = (1..52).random()
        image = when (randomNumber) {
            1 -> R.drawable.heartace
            2 -> R.drawable.heart2
            3 -> R.drawable.heart3
            4 -> R.drawable.heart4
            5 -> R.drawable.heart5
            6 -> R.drawable.heart6
            7 -> R.drawable.heart7
            8 -> R.drawable.heart8
            9 -> R.drawable.heart9
            10 -> R.drawable.heart10
            11 -> R.drawable.heartj
            12 -> R.drawable.heartq
            13 -> R.drawable.heartk
            14 -> R.drawable.clubace
            15 -> R.drawable.club2
            16 -> R.drawable.club3
            17 -> R.drawable.club4
            18 -> R.drawable.club5
            19 -> R.drawable.club6
            20 -> R.drawable.club7
            21 -> R.drawable.club8
            22 -> R.drawable.club9
            23 -> R.drawable.club10
            24 -> R.drawable.clubj
            25 -> R.drawable.clubq
            26 -> R.drawable.clubk
            27 -> R.drawable.diamondsace
            28 -> R.drawable.diamonds2
            29 -> R.drawable.diamonds3
            30 -> R.drawable.diamonds4
            31 -> R.drawable.diamonds5
            32 -> R.drawable.diamonds6
            33 -> R.drawable.diamonds7
            34 -> R.drawable.diamonds8
            35 -> R.drawable.diamonds9
            36 -> R.drawable.diamonds10
            37 -> R.drawable.diamondsj
            38 -> R.drawable.diamondsq
            39 -> R.drawable.diamondsk
            40 -> R.drawable.spadesace
            41 -> R.drawable.spades2
            42 -> R.drawable.spades3
            43 -> R.drawable.spades4
            44 -> R.drawable.spades5
            45 -> R.drawable.spades6
            46 -> R.drawable.spades7
            47 -> R.drawable.spades8
            48 -> R.drawable.spades9
            49 -> R.drawable.spades10
            50 -> R.drawable.spadesj
            51 -> R.drawable.spadesq
            else -> R.drawable.spadesk
        }
        imageViewSecretCard.setImageResource(image)
    }

    fun changeImages () {
        val handler = Handler()
        changeSecretImage()
        handler.postDelayed( {
            imageViewSecretCard.setImageResource(R.drawable.cardback)
            imageViewOldCard.setImageResource(image)
        },2000)


    }

}
