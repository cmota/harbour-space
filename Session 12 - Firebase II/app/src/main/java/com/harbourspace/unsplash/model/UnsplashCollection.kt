package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harbourspace.unsplash.model.converters.CoverPhotoConverter
import com.harbourspace.unsplash.model.converters.LinksConverter
import com.harbourspace.unsplash.model.converters.UserConverter
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName

@Entity(tableName = "UnsplashCollection")
@Parcelize
data class UnsplashCollection(
    @field:TypeConverters(CoverPhotoConverter::class)
    val cover_photo: CoverPhoto,

    val description: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val last_collected_at: String,

    @field:TypeConverters(LinksConverter::class)
    val links: Links,

    @SerialName("private")
    val is_private: Boolean,
    val published_at: String,
    val share_key: String,
    val title: String,
    val total_photos: Int,
    val updated_at: String,

    @field:TypeConverters(UserConverter::class)
    val user: User
): Parcelable