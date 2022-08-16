package com.harbourspace.unsplash.model.converters

import androidx.room.TypeConverter
import com.harbourspace.unsplash.model.Location
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocationConverter {

    @TypeConverter
    fun fromLocationType(value: Location?): String = Json.encodeToString(value)

    @TypeConverter
    fun toLocationType(value: String): Location? = Json.decodeFromString(value)
}