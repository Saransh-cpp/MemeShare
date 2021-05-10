package com.example.memeshare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MemeScreen::class.java)

        memeButton.setOnClickListener {
            intent.putExtra("APIEndpoint", "memes")
            startActivity(intent)
        }

        dankMemesButton.setOnClickListener {
            intent.putExtra("APIEndpoint", "dankmemes")
            startActivity(intent)
        }

    }



}