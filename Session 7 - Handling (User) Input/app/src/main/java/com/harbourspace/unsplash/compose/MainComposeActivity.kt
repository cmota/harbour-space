package com.harbourspace.unsplash.compose

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.harbourspace.unsplash.DetailsActivity
import com.harbourspace.unsplash.R
import com.harbourspace.unsplash.UnsplashViewModel
import com.harbourspace.unsplash.model.UnsplashItem

class MainComposeActivity : AppCompatActivity() {

    private val unsplashViewModel: UnsplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        unsplashViewModel.fetchImages()

        setContent {

            val unsplashItems = unsplashViewModel.unsplashItems.observeAsState()

            unsplashViewModel.error.observe(this) {
                Toast.makeText(baseContext, R.string.main_unable_to_fetch_images, Toast.LENGTH_SHORT).show()
            }

            MaterialTheme {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                ) {
                    item {
                        AddUserInputAction(
                            onSearchAction = {
                                unsplashViewModel.searchImages(it)
                            }
                        )
                    }
                    
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    
                    items(unsplashItems.value ?: emptyList()) {

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
    fun AddUserInputAction(
        onSearchAction: (String) -> Unit
    ) {
        val search = remember { mutableStateOf("") }

        OutlinedTextField(
            value = search.value,
            onValueChange = { value ->
                search.value = value
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.main_search_hint),
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            },
            leadingIcon = {
                val image = painterResource(id = R.drawable.ic_search)
                val description = stringResource(R.string.description_search)

                Image(
                    painter = image,
                    contentDescription = description
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions {
                onSearchAction(search.value)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.LightGray
            )
        )
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
            backgroundColor = Color.Transparent
        ) {

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image.urls.regular)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = stringResource(id = R.string.description_image_preview),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

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
                    fontWeight = FontWeight.Light,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = image.user.name ?: "",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    private fun openDetailsActivity() {
        startActivity(Intent(this, DetailsActivity::class.java))
    }
}