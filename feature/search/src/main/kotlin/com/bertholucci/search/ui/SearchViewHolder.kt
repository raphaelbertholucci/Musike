package com.bertholucci.search.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.search.R
import com.bertholucci.search.databinding.SearchItemMusicBinding
import com.bertholucci.search.extensions.loadFromUrl
import com.bertholucci.search.model.Music

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = SearchItemMusicBinding.bind(itemView)

    fun bind(data: Music) {
        binding.tvArtist.text = data.artist
        binding.tvTitle.text = data.name
        binding.tvListeners.text = data.listeners

        when {
            data.image.isNotEmpty() -> binding.ivMusic.loadFromUrl(data.image.first().image)
            else -> binding.ivMusic.setImageResource(R.drawable.img_music_placeholder)
        }
    }
}