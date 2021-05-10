package com.example.memeshare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = CategoryAdapter(this)
        recyclerView.adapter = mAdapter

//        memeButton.setOnClickListener {
//            intent.putExtra("APIEndpoint", "memes")
//            startActivity(intent)
//        }
//
//        dankMemesButton.setOnClickListener {
//            intent.putExtra("APIEndpoint", "dankmemes")
//            startActivity(intent)
//        }

    }

    override fun onItemClicked(item: String) {
        val intent = Intent(this, MemeScreen::class.java)
        intent.putExtra("APIEndpoint", item)
        startActivity(intent)
    }
}