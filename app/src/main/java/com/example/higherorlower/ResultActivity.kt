package com.example.higherorlower

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        resultTextView = findViewById(R.id.resultTextView)

        val point = getPoint()
        val highScore = getPreviousHighscore()
        if (point == highScore){
            resultTextView.text = getString(R.string.result_textview, "Grattis! Du satte det nya rekordet på $highScore!")
            Log.d("!!!", "Grattis! Du satte rekordet på $highScore")
        }
        else {
            resultTextView.text = getString(R.string.result_textview,"Tyvärr räckte inte din poäng $point för att slå" +
                    "rekordet $highScore")
            Log.d("!!!", "Tyvärr räckte det inte. Rekord: $highScore, du lyckades få $point poäng")
        }
    }


    fun getPoint (): Int {
        val point = intent.getIntExtra("point", 0)
        return point
    }
    fun getPreviousHighscore() : Int {
        val previousHighScore = intent.getIntExtra("highscore", 0)
        return previousHighScore
    }
}
