package com.harbourspace.unsplash.model

import androidx.room.TypeConverter

class UnsplashItemConverter {

    /*@TypeConverter
    fun fromOpeningHoursDayArray(ohda: Array<OpeningHoursDay?>?): List<OpeningHoursDay>? {
        val ohdList: MutableList<OpeningHoursDay> = ArrayList()
        ohdList.addAll(Arrays.asList(ohda))
        return ohdList
    }

    @TypeConverter
    fun toOpeningHoursDayArray(ohdList: List<OpeningHoursDay>): Array<OpeningHoursDay>? {
        val ohda: Array<OpeningHoursDay>? = null
        for (i in ohdList.indices) {
            ohda!![i] = ohdList[i]
        }
        return ohda
    }*/
}