package com.karlosprojects.searchimagesapp.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.karlosprojects.searchimagesapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    private val viewModel by viewModels<GalleryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** its important to pass viewLifecycleOwner to the livedata method and not the fragment itself
        because we want to stop updating our UI when the view of the fragment is destroyed (ex. when fragment is putting to the backstack) **/
        viewModel.photos.observe(viewLifecycleOwner) {

        }
    }
}