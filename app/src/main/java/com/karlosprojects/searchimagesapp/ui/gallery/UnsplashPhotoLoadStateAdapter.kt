package com.karlosprojects.searchimagesapp.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karlosprojects.searchimagesapp.databinding.UnsplashPhotoLoadStateFooterBinding

class UnsplashPhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UnsplashPhotoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = UnsplashPhotoLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: UnsplashPhotoLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*avoid including a clickListener on the onBindViewHolder method, because it will be repeated
        over and over again for each item that is scrolled into the screen, so init is the best place to
        add the listeners so it will not be repeated*/
        init {
            binding.loadStateBtnRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                loadStateProgressbar.isVisible = loadState is LoadState.Loading
                loadStateBtnRetry.isVisible = loadState !is LoadState.Loading
                loadStateTxtError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}