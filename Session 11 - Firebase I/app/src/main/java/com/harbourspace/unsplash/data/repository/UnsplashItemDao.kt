package com.harbourspace.unsplash.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harbourspace.unsplash.model.UnsplashItem

@Dao
interface UnsplashItemDao {

    @Query("SELECT * FROM UnsplashItem")
    fun getItems(): LiveData<List<UnsplashItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertItem(item: UnsplashItem)

    @Insert
    fun insertItems(items: List<UnsplashItem>)
}