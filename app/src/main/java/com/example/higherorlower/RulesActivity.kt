package com.example.higherorlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)

        val backImageButton = findViewById<ImageButton>(R.id.backImageButton3)

        backImageButton.setOnClickListener {
            finish()
        }
    }
}