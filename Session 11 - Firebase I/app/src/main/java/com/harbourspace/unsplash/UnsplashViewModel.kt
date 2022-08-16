package com.harbourspace.unsplash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harbourspace.unsplash.data.UnsplashApiProvider
import com.harbourspace.unsplash.data.cb.UnsplashResult
import com.harbourspace.unsplash.data.repository.UnsplashItemRepository
import com.harbourspace.unsplash.model.UnsplashCollection
import com.harbourspace.unsplash.model.UnsplashItem
import com.harbourspace.unsplash.utils.combineLatestData

private const val TAG = "UnsplashViewModel"

class UnsplashViewModel(
    private val repository: UnsplashItemRepository
): ViewModel(), UnsplashResult, ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnsplashViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnsplashViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }

    private val _unsplashItems = MutableLiveData<List<UnsplashItem>>()
    val unsplashItems: LiveData<List<UnsplashItem>> = _unsplashItems

    private val _unsplashCollections = MutableLiveData<List<UnsplashCollection>>()
    val unsplashCollections: LiveData<List<UnsplashCollection>> = _unsplashCollections

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val provider by lazy {
        UnsplashApiProvider()
    }

    fun fetchImages(): LiveData<List<UnsplashItem>> {
        provider.fetchImages(this)

        val result = MediatorLiveData<List<UnsplashItem>>()

        result.addSource(_unsplashItems) {
            result.value = combineLatestData(_unsplashItems, repository.allItems)
        }

        result.addSource(repository.allItems) {
            result.value = combineLatestData(_unsplashItems, repository.allItems)
        }

        return result
    }

    fun forceFetchImages() {
        _unsplashItems.value = emptyList()
        provider.fetchImages(this)
    }

    fun searchImages(keyword: String) {
        provider.searchImages(keyword, this)
    }

    fun fetchCollections() {
        provider.fetchCollections(this)
    }

    override fun onDataFetchedSuccess(images: List<UnsplashItem>) {
        Log.d(TAG, "onDataFetchedSuccess | Received ${images.size} images")
        _unsplashItems.value = images
        repository.insertItems(images)
    }

    override fun onCollectionsFetchedSuccess(collections: List<UnsplashCollection>) {
        Log.d(TAG, "onCollectionsFetchedSuccess | Received ${collections.size} images")
        _unsplashCollections.value = collections
    }

    override fun onDataFetchedFailed() {
        Log.d(TAG, "onDataFetchedFailed | Unable to retrieve images")
        _error.value = true
        _unsplashItems.value = emptyList()
    }
}