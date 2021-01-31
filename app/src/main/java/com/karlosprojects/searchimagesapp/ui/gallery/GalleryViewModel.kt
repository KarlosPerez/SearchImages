package com.karlosprojects.searchimagesapp.ui.gallery

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.karlosprojects.searchimagesapp.data.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    /**
     * with swithMap this will be executed whenever the value of the livedata changes; when it happens
     * we get passed a parameter that contains the new value of currentQuery in form of String
     *
     * After we get the result, we cache the livedata, otherwise we get a crash when configuration changes
     */
    val photos = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchPhotos(query : String) {
        currentQuery.value = query
    }

    companion object {
        private const val DEFAULT_QUERY = "dogs"
    }
}