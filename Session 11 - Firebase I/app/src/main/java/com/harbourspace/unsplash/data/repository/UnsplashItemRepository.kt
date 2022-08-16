package com.harbourspace.unsplash.data.repository

import androidx.lifecycle.LiveData
import com.harbourspace.unsplash.model.UnsplashItem

class UnsplashItemRepository(private val unsplashDao: UnsplashItemDao) {

    val allItems: LiveData<List<UnsplashItem>> = unsplashDao.getItems()

    fun insertItem(item: UnsplashItem) {
        AppDatabase.databaseWriteExecutor.execute {
            unsplashDao.insertItem(item)
        }
    }

    fun insertItems(items: List<UnsplashItem>) {
        AppDatabase.databaseWriteExecutor.execute {
            unsplashDao.insertItems(items)
        }
    }
}