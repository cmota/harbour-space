package com.harbourspace.unsplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harbourspace.unsplash.data.UnsplashApiProvider
import com.harbourspace.unsplash.data.cb.UnsplashResult
import com.harbourspace.unsplash.model.UnsplashItem

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), UnsplashResult {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val provider = UnsplashApiProvider()
        provider.fetchImages(this)

        findViewById<RecyclerView>(R.id.rv_container).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(emptyList()) {
                Toast.makeText(context, getString(R.string.main_item_clicked, it), Toast.LENGTH_SHORT).show()
                openDetailsActivity()
            }
        }
    }

    private fun openDetailsActivity() {
        startActivity(Intent(this, DetailsActivity::class.java))
    }

    override fun onDataFetchedSuccess(images: List<UnsplashItem>) {
        val adapter = findViewById<RecyclerView>(R.id.rv_container).adapter as MainAdapter
        adapter.submitList(images)
    }

    override fun onDataFetchedFailed() {
        Toast.makeText(baseContext, R.string.main_unable_to_fetch_images, Toast.LENGTH_SHORT).show()
    }
}