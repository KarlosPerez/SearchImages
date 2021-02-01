package com.karlosprojects.searchimagesapp.ui.gallery

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.karlosprojects.searchimagesapp.data.UnsplashRepository

class GalleryViewModel @ViewModelInject constructor(
    private val repository: UnsplashRepository,
    @Assisted state : SavedStateHandle
) : ViewModel() {

    /**
     * We use state instead of MutableLiveData, just for handling process death purposes
     */
    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

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
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "dogs"
    }
}