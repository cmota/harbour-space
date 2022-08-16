package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "CurrentUserCollection")
@Parcelize
@Serializable
data class CurrentUserCollection(
    val cover_photo: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val last_collected_at: String,
    val published_at: String,
    val title: String,
    val updated_at: String,
    val user: String
) : Parcelable