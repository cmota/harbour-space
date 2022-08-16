package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harbourspace.unsplash.model.converters.LinksConverter
import com.harbourspace.unsplash.model.converters.ProfileImageConverter
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "User")
@Parcelize
@Serializable
data class User(
    val bio: String?,

    @PrimaryKey(autoGenerate = false)
    val id: String,

    val instagram_username: String?,

    @field:TypeConverters(LinksConverter::class)
    val links: Links,

    val location: String?,
    val name: String,
    val portfolio_url: String?,

    @field:TypeConverters(ProfileImageConverter::class)
    val profile_image: ProfileImage,

    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String?,
    val username: String
) : Parcelable