package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harbourspace.unsplash.model.converters.ExifConverter
import com.harbourspace.unsplash.model.converters.LinksConverter
import com.harbourspace.unsplash.model.converters.ListCurrentUserCollectionConverter
import com.harbourspace.unsplash.model.converters.LocationConverter
import com.harbourspace.unsplash.model.converters.UrlsConverter
import com.harbourspace.unsplash.model.converters.UserConverter
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "UnsplashItem")
@Parcelize
data class UnsplashItem(
    val blur_hash: String,
    val color: String,
    val created_at: String,

    @field:TypeConverters(ListCurrentUserCollectionConverter::class)
    val current_user_collections: List<CurrentUserCollection>,

    val description: String?,
    val height: Int,

    @PrimaryKey(autoGenerate = false)
    val id: String,

    val liked_by_user: Boolean,
    val likes: Int,

    @field:TypeConverters(LinksConverter::class)
    val links: Links,

    val updated_at: String,

    @field:TypeConverters(UrlsConverter::class)
    val urls: Urls,

    @field:TypeConverters(UserConverter::class)
    val user: User,

    val width: Int,

    @field:TypeConverters(ExifConverter::class)
    val exif: Exif?,

    @field:TypeConverters(LocationConverter::class)
    val location: Location?
) : Parcelable