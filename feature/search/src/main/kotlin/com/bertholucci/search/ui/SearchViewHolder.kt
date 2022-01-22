package com.bertholucci.search.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.search.databinding.SearchItemMusicBinding
import com.bertholucci.search.extensions.loadFromUrl
import com.bertholucci.search.model.Music

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = SearchItemMusicBinding.bind(itemView)

    fun bind(data: Music) {
        binding.tvArtist.text = data.artist
        binding.tvTitle.text = data.name
        binding.ivMusic.loadFromUrl(data.image.first().image)
    }
}