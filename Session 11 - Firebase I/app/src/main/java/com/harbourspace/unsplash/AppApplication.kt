package com.harbourspace.unsplash

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import com.harbourspace.unsplash.data.repository.AppDatabase
import com.harbourspace.unsplash.data.repository.UnsplashItemRepository

class AppApplication : Application(), ImageLoaderFactory {

    private val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { UnsplashItemRepository(database.unsplashDao()) }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(filesDir.resolve("image_cache"))
                    .build()
            }
            .build()
    }
}