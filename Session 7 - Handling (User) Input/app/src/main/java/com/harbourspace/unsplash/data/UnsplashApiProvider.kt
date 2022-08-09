package com.harbourspace.unsplash.data

import android.util.Log
import com.harbourspace.unsplash.data.cb.UnsplashResult
import com.harbourspace.unsplash.model.UnsplashItem
import com.harbourspace.unsplash.model.UnsplashSearch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val TAG = "UnsplashApiProvider"
private const val BASE_URL = "https://api.unsplash.com/"

class UnsplashApiProvider {

    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create<UnsplashAPIClient>()
    }

    fun fetchImages(cb: UnsplashResult) {
        retrofit.fetchPhotos().enqueue(object : Callback<List<UnsplashItem>> {
            override fun onResponse(call: Call<List<UnsplashItem>>, response: Response<List<UnsplashItem>>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "fetchImages | response: ${response.body()}")
                    cb.onDataFetchedSuccess(response.body()!!)
                } else {
                    cb.onDataFetchedFailed()
                }
            }

            override fun onFailure(call: Call<List<UnsplashItem>>, t: Throwable) {
                Log.e(TAG, "fetchImages | error loading images", t)
                cb.onDataFetchedFailed()
            }
        })
    }

    fun searchImages(keyword: String, cb: UnsplashResult) {
        retrofit.searchPhotos(keyword).enqueue(object : Callback<UnsplashSearch> {
            override fun onResponse(call: Call<UnsplashSearch>, response: Response<UnsplashSearch>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d(TAG, "searchImages | response: ${response.body()}")
                    cb.onDataFetchedSuccess(response.body()!!.results)
                } else {
                    cb.onDataFetchedFailed()
                }
            }

            override fun onFailure(call: Call<UnsplashSearch>, t: Throwable) {
                Log.e(TAG, "searchImages | error loading images", t)
                cb.onDataFetchedFailed()
            }
        })
    }
}