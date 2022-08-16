package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "Location")
@Parcelize
@Serializable
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val country: String
) : Parcelable