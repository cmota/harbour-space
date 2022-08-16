package com.harbourspace.unsplash.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.harbourspace.unsplash.model.CoverPhoto
import com.harbourspace.unsplash.model.CurrentUserCollection
import com.harbourspace.unsplash.model.Exif
import com.harbourspace.unsplash.model.Links
import com.harbourspace.unsplash.model.Location
import com.harbourspace.unsplash.model.ProfileImage
import com.harbourspace.unsplash.model.UnsplashCollection
import com.harbourspace.unsplash.model.UnsplashItem
import com.harbourspace.unsplash.model.Urls
import com.harbourspace.unsplash.model.User
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [ UnsplashItem::class, ], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun unsplashDao(): UnsplashItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(context, AppDatabase::class.java, "unsplash_db").build()
                INSTANCE = db
                db
            }
        }

        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(2)
    }
}