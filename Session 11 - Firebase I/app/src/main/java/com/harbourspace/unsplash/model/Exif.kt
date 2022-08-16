package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "Exif")
@Parcelize
@Serializable
data class Exif(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val make: String,
    val model: String,
    val name: String,
    val exposure_time: Float,
    val aperture: Double,
    val focal_length: Int,
    val iso: Int
) : Parcelable