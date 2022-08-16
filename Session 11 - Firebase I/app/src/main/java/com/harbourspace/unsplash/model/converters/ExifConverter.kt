package com.harbourspace.unsplash.model.converters

import androidx.room.TypeConverter
import com.harbourspace.unsplash.model.Exif
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ExifConverter {

    @TypeConverter
    fun fromExifType(value: Exif?): String = Json.encodeToString(value)

    @TypeConverter
    fun toExifType(value: String): Exif? = Json.decodeFromString(value)
}