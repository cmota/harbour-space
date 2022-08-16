package com.harbourspace.unsplash.utils

import androidx.lifecycle.LiveData
import com.harbourspace.unsplash.model.UnsplashItem

fun combineLatestData(
    networkResult: LiveData<List<UnsplashItem>>,
    databaseResult: LiveData<List<UnsplashItem>>
): List<UnsplashItem> {

    val network = networkResult.value
    val database = databaseResult.value

    if (network == null || database == null) {
        return emptyList()
    }

    return network.plus(database)
}