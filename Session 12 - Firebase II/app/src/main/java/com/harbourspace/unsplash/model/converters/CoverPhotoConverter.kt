package com.harbourspace.unsplash.model.converters

import androidx.room.TypeConverter
import com.harbourspace.unsplash.model.CoverPhoto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CoverPhotoConverter {

    @TypeConverter
    fun fromCoverPhotoType(value: CoverPhoto?): String = Json.encodeToString(value)

    @TypeConverter
    fun toCoverPhotoType(value: String): CoverPhoto? = Json.decodeFromString(value)
}