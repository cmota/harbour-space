package com.harbourspace.unsplash.compose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.harbourspace.unsplash.DetailsActivity
import com.harbourspace.unsplash.R

class MainComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val images = listOf(
            R.drawable.bcn_la_sagrada_familia,
            R.drawable.bcn_casa_battlo,
            R.drawable.bcn_parc_guell,
            R.drawable.bcn_palau_montjuic,
            R.drawable.bcn_parc_guell_2
        )

        setContent {

            MaterialTheme {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    items(images) {
                        val context = LocalContext.current

                        Image(
                            painter = painterResource(id = it),
                            contentDescription = stringResource(id = R.string.description_image_preview),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(top = 8.dp, bottom = 8.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable {
                                Toast.makeText(context, getString(R.string.main_item_clicked, it), Toast.LENGTH_SHORT).show()
                                openDetailsActivity()
                            },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }

    private fun openDetailsActivity() {
        startActivity(Intent(this, DetailsActivity::class.java))
    }
}