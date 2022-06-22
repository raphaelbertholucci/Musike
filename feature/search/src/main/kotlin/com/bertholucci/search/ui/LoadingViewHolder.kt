package com.bertholucci.search.ui

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.search.databinding.SearchItemLoadingBinding

class LoadingViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private var binding = SearchItemLoadingBinding.bind(itemView)

    fun bind(isFullyLoaded: Boolean) {
        binding.loading.isVisible = isFullyLoaded.not()
        binding.tvFullyLoaded.isVisible = isFullyLoaded
    }
}
