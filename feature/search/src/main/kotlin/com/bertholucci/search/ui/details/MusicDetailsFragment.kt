package com.bertholucci.search.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bertholucci.common.base.BaseFragment
import com.bertholucci.search.R
import com.bertholucci.search.databinding.SearchFragmentMusicDetailsBinding
import com.bertholucci.search.extensions.loadFromUrl

class MusicDetailsFragment : BaseFragment<SearchFragmentMusicDetailsBinding>() {

    private val args: MusicDetailsFragmentArgs by navArgs()

    override fun getViewBinding() =
        SearchFragmentMusicDetailsBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        addListeners()
    }

    private fun addListeners() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setupUI() {
        val music = args.music
        binding.tvSong.text = music.name
        binding.tvArtist.text = music.artist
        binding.tvListeners.text = music.listeners

        when {
            music.image.isNotEmpty() -> binding.ivMusic.loadFromUrl(music.image.last().image)
            else -> binding.ivMusic.setImageResource(R.drawable.img_music_placeholder)
        }
    }
}