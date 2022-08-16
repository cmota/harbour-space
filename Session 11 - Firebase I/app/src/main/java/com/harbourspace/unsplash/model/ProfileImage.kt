package com.harbourspace.unsplash.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Entity(tableName = "ProfileImage")
@Parcelize
@Serializable
data class ProfileImage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val large: String,
    val medium: String,
    val small: String
) : Parcelable