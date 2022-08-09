package com.harbourspace.unsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.harbourspace.unsplash.model.UnsplashItem
import com.harbourspace.unsplash.utils.EXTRA_UNSPLASH_IMAGE

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val image = if (intent.extras?.containsKey(EXTRA_UNSPLASH_IMAGE) == true) {
            intent.extras!!.get(EXTRA_UNSPLASH_IMAGE) as UnsplashItem
        } else{
            finish()
            return
        }

        findViewById<ImageView>(R.id.iv_preview).load(image.urls.regular) {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
        }
    }
}