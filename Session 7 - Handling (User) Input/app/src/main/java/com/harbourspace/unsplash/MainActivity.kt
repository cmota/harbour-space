package com.harbourspace.unsplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harbourspace.unsplash.model.UnsplashItem
import com.harbourspace.unsplash.utils.EXTRA_UNSPLASH_IMAGE

class MainActivity : AppCompatActivity() {

    private val unsplashViewModel : UnsplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.et_search).setOnEditorActionListener { textView, i, keyEvent ->
            val keyword = findViewById<EditText>(R.id.et_search).text.toString()
            unsplashViewModel.searchImages(keyword)
            true
        }

        findViewById<RecyclerView>(R.id.rv_container).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(emptyList()) {
                openDetailsActivity(it)
            }
        }

        unsplashViewModel.unsplashItems.observe(this) {
            val adapter = findViewById<RecyclerView>(R.id.rv_container).adapter as MainAdapter
            adapter.submitList(it)
        }

        unsplashViewModel.error.observe(this) {
            Toast.makeText(baseContext, R.string.main_unable_to_fetch_images, Toast.LENGTH_SHORT).show()
        }

        unsplashViewModel.fetchImages()
    }

    private fun openDetailsActivity(image: UnsplashItem) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_UNSPLASH_IMAGE, image)

        startActivity(intent)
    }
}