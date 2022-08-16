package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "Urls")
@Parcelize
@Serializable
data class Urls(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val full: String,
    val raw: String,
    val regular: String,
    val small: String,
    val thumb: String
) : Parcelable