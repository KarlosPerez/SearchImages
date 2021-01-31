package com.karlosprojects.searchimagesapp.api

import com.karlosprojects.searchimagesapp.data.UnsplashPhoto

data class UnsplashResponse(
    val results : List<UnsplashPhoto>
)