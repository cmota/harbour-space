package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harbourspace.unsplash.model.converters.LinksConverter
import com.harbourspace.unsplash.model.converters.UrlsConverter
import com.harbourspace.unsplash.model.converters.UserConverter
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "CoverPhoto")
@Parcelize
@Serializable
data class CoverPhoto(
    val blur_hash: String,
    val color: String,
    val description: String?,
    val height: Int,

    @PrimaryKey(autoGenerate = false)
    val id: String,

    val liked_by_user: Boolean,
    val likes: Int,

    @field:TypeConverters(LinksConverter::class)
    val links: Links,

    @field:TypeConverters(UrlsConverter::class)
    val urls: Urls,

    @field:TypeConverters(UserConverter::class)
    val user: User,

    val width: Int
): Parcelable