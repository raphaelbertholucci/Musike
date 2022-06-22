package com.bertholucci.search.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bertholucci.common.base.BaseFragment
import com.bertholucci.search.R
import com.bertholucci.search.databinding.SearchFragmentMusicDetailsBinding
import com.bertholucci.search.extensions.isFavorite
import com.bertholucci.search.extensions.loadFromUrl
import com.bertholucci.search.extensions.showSnack
import com.bertholucci.search.model.Track
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TrackDetailsFragment : BaseFragment<SearchFragmentMusicDetailsBinding>() {

    private val args: TrackDetailsFragmentArgs by navArgs()
    private val viewModel: TrackDetailsViewModel by viewModel {
        parametersOf(args.track)
    }

    override fun getViewBinding() =
        SearchFragmentMusicDetailsBinding.inflate(LayoutInflater.from(context))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        addListeners()
    }

    private fun addObservers() {
        viewModel.track.observe(viewLifecycleOwner) { track ->
            setupUI(track)
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) {
            checkIfTrackIsFavorite(it)
            binding.ivSave.isFavorite(it)
        }
    }

    private fun addListeners() {
        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.ivSave.setOnClickListener {
            viewModel.updateTrackState()
        }
    }

    private fun setupUI(track: Track) {
        binding.tvSong.text = track.name
        binding.tvArtist.text = track.artist
        binding.tvListeners.text = track.listeners
        binding.ivSave.isFavorite(track.isFavorite)

        when {
            track.image.isNotEmpty() -> binding.ivMusic.loadFromUrl(track.image.last().image)
            else -> binding.ivMusic.setImageResource(R.drawable.img_music_placeholder)
        }
    }

    private fun checkIfTrackIsFavorite(isFavorite: Boolean) {
        showSnack(
            resId = if (isFavorite) R.string.search_music_details_saved
            else R.string.search_music_details_removed
        )
    }
}
