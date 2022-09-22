package com.example.higherorlower

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

//        val point = intent.getIntExtra("point", 0)
//        val previousHighScore = intent.getIntExtra("highscore", 0)

        var point = getPoint()
        var highscore = getPreviousHighscore()
        if (point == highscore){
            Log.d("!!!", "Grattis! Du satte rekordet på $highscore")
        }
        else {
            Log.d("!!!", "Tyvärr räckte det inte. Rekord: $highscore, du lyckades få $point poäng")
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
