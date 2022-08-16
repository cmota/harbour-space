package com.harbourspace.unsplash.model.converters

import androidx.room.TypeConverter
import com.harbourspace.unsplash.model.CurrentUserCollection
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class ListCurrentUserCollectionConverter {

    @TypeConverter
    fun fromListCurrentUserCollectionType(value: List<CurrentUserCollection>?): String = Json.encodeToString(value)

    @TypeConverter
    fun toListCurrentUserCollectionType(value: String): List<CurrentUserCollection>? = Json.decodeFromString(value)
}