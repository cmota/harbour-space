package com.harbourspace.unsplash.compose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harbourspace.unsplash.DetailsActivity
import com.harbourspace.unsplash.R
import com.harbourspace.unsplash.data.UnsplashApiProvider
import com.harbourspace.unsplash.data.cb.UnsplashResult
import com.harbourspace.unsplash.model.UnsplashItem

class MainComposeActivity : AppCompatActivity(), UnsplashResult {

    private lateinit var unsplashItems: MutableState<List<UnsplashItem>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val provider = UnsplashApiProvider()
        provider.fetchImages(this)

        setContent {

            unsplashItems = remember { mutableStateOf(emptyList()) }

            MaterialTheme {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    items(unsplashItems.value) {

                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            AddUnsplashImage(it)

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun AddUnsplashImage(image: UnsplashItem) {
        val context = LocalContext.current

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {
                    Toast
                        .makeText(
                            context,
                            getString(R.string.main_item_clicked, image.hashCode()),
                            Toast.LENGTH_SHORT
                        )
                        .show()
                    openDetailsActivity()
                },
            backgroundColor = colorResource(id = R.color.purple_500)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Bottom
            ) {

                Text(
                    text = image.description ?: "",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = image.user.name ?: "",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    private fun openDetailsActivity() {
        startActivity(Intent(this, DetailsActivity::class.java))
    }

    override fun onDataFetchedSuccess(images: List<UnsplashItem>) {
        unsplashItems.value = images
    }

    override fun onDataFetchedFailed() {
        Toast.makeText(baseContext, R.string.main_unable_to_fetch_images, Toast.LENGTH_SHORT).show()
    }
}