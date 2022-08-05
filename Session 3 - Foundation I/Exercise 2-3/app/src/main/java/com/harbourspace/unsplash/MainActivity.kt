package com.harbourspace.unsplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.iv_preview).setOnClickListener {
            openDetailsActivity()
        }
    }

    private fun openDetailsActivity() {
        startActivity(Intent(this, DetailsActivity::class.java))
    }
}