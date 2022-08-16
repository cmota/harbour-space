package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "Links")
@Parcelize
@Serializable
data class Links(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val download: String?,
    val download_location: String?,
    val html: String,
    val self: String
) : Parcelable