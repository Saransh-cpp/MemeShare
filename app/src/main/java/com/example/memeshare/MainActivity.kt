package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {

    var currentImageURL: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
    }


    private fun loadMeme() {

        val meme = findViewById<ImageView>(R.id.meme)
        val progressBar1 = findViewById<ProgressBar>(R.id.progressBar)
        progressBar1.visibility = View.VISIBLE

        // Instantiate the RequestQueue.
        currentImageURL = "https://meme-api.herokuapp.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, currentImageURL, null,
                { response ->
                    currentImageURL = response.getString("url")
                    Glide.with(this).load(currentImageURL).
                    listener(object: RequestListener<Drawable>{
                        override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                        ): Boolean {
                            progressBar1.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                        ): Boolean {
                            progressBar1.visibility = View.GONE
                            return false
                        }

                    }).into(meme)

                },
                {
                    Toast.makeText(this, "Oops! Something went wrong :(", Toast.LENGTH_LONG).show()

                })

        // Add the request to the RequestQueue.
        MySingleton.MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    fun shareMeme(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout this meme $currentImageURL")
        val chooser = Intent.createChooser(intent, "Share this meme using?")
        startActivity(chooser)
    }
    fun nextMeme(view: View) {
        loadMeme()
    }
}