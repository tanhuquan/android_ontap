package com.example.bai2

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DictionaryDetail : AppCompatActivity() {
    lateinit var verb: TextView
    lateinit var verbType: TextView
    lateinit var meaning: TextView
    lateinit var btnQuit: Button
    lateinit var minhhoa: ImageView

    val img = mutableListOf<Int>(
        R.drawable.img,
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
        R.drawable.img_6,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dictionary_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        verb = findViewById(R.id.verb)
        verbType = findViewById(R.id.verbType)
        meaning = findViewById(R.id.meaning)
        minhhoa = findViewById(R.id.minhhoa)
        val randomImage = img.random()
        minhhoa.setImageResource(randomImage)
        btnQuit = findViewById(R.id.btnQuit)
        btnQuit.setOnClickListener {
            finish()
        }

        val english = intent.getStringExtra("ENGLISH") ?: ""
        val verbTypeVal = intent.getStringExtra("VERB_TYPE") ?: ""
        val meaningVal = intent.getStringExtra("VIETNAMESE") ?: ""
        verb.text = english
        verbType.text = verbTypeVal
        meaning.text = meaningVal
    }
}