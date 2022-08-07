package com.harbourspace.unsplash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val images = listOf(
            R.drawable.bcn_la_sagrada_familia,
            R.drawable.bcn_casa_battlo,
            R.drawable.bcn_parc_guell,
            R.drawable.bcn_palau_montjuic,
            R.drawable.bcn_parc_guell_2
        )

        findViewById<RecyclerView>(R.id.rv_container).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MainAdapter(images) {
                Toast.makeText(context, getString(R.string.main_item_clicked, it), Toast.LENGTH_SHORT).show()
                openDetailsActivity()
            }
        }
    }

    private fun openDetailsActivity() {
        startActivity(Intent(this, DetailsActivity::class.java))
    }
}