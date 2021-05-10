package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_meme_screen.*

class MemeScreen : AppCompatActivity() {
    var currentImageURL: String? = null
    var subReddit: String? = "meme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meme_screen)

        subReddit = intent.getStringExtra("APIEndpoint")
        loadMeme(subReddit)
        progressBar.visibility = View.VISIBLE
    }

    private fun loadMeme(subReddit: String?) {

        progressBar.visibility = View.VISIBLE

        // Instantiate the RequestQueue.
        currentImageURL = "https://meme-boii.herokuapp.com/${subReddit}/50"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, currentImageURL, null,
            { response ->
                currentImageURL = response.getString("url")
                Glide.with(this).load(currentImageURL).
                listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
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
        loadMeme(subReddit)
    }
}