package com.bertholucci.search.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bertholucci.common.base.BaseActivity
import com.bertholucci.common.helpers.EndlessScrollListener
import com.bertholucci.common.helpers.NetworkHelper.hasConnection
import com.bertholucci.common.helpers.fold
import com.bertholucci.search.R
import com.bertholucci.search.databinding.SearchActivityBinding
import com.bertholucci.search.model.Music
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<SearchActivityBinding>() {

    private val viewModel: SearchViewModel by viewModel()

    private val adapter = SearchAdapter()
    private lateinit var listener: EndlessScrollListener

    override fun getViewBinding() = SearchActivityBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObservers()
        addListeners()
        setupUI()
    }

    private fun addObservers() {
        viewModel.tracks.observe(this) { response ->
            binding.swipe.isRefreshing = false
            response.fold(
                error = ::handleError,
                loading = ::handleLoading,
                success = ::handleSuccess
            )
        }
    }

    private fun handleSuccess(list: List<Music>) {
        when {
            list.isEmpty() -> {
                setupUIWithEmptyList()
            }
            viewModel.isFirstPage() && list.size < 20 -> {
                adapter.isFullyLoaded = true
                display(content = true)
            }
            else -> {
                adapter.isFullyLoaded = false
                display(content = true)
            }
        }
        adapter.updateList(list, viewModel.page.value)
    }

    private fun setupUIWithEmptyList() {
        when {
            viewModel.isFirstPage() -> display(empty = true)
            else -> adapter.isFullyLoaded = true
        }
    }

    private fun handleLoading(loading: Boolean) {
        if (loading && viewModel.isFirstPage()) {
            display(loading = true)
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERROR", throwable.message, throwable)
        if (hasConnection(this).not())
            binding.error.tvError.setText(R.string.common_error_internet)
        display(error = true)
        binding.rvTracks.smoothScrollToPosition(0)
    }

    private fun display(
        content: Boolean = false,
        loading: Boolean = false,
        empty: Boolean = false,
        error: Boolean = false
    ) {
        binding.loading.shimmer.isVisible = loading
        binding.rvTracks.isVisible = content
        binding.empty.root.isVisible = empty
        binding.error.root.isVisible = error
    }

    private fun addListeners() {
        binding.etSearch.addTextChangedListener {
            it?.let { text -> searchTrack(text.toString()) }
        }

        binding.swipe.setOnRefreshListener {
            searchTrack(binding.etSearch.text.toString())
        }

        binding.tvSort.setOnClickListener {
            SearchPopularityDialog(onSelectOption = { isCrescent ->
                viewModel.updatePopularityChoice(isCrescent)
            }).show(supportFragmentManager, "POPULARITY_DIALOG")
        }
    }

    private fun searchTrack(text: String) {
        if (text.length >= 3) {
            viewModel.getTracksByName(text)
            listener.reset()
        } else {
            binding.swipe.isRefreshing = false
        }
    }

    private fun setupEndlessScroll(): EndlessScrollListener {
        return object :
            EndlessScrollListener(binding.rvTracks.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int) {
                viewModel.updatePage(page)
                viewModel.getTracksByName(binding.etSearch.text.toString(), page)
            }
        }
    }

    private fun setupUI() {
        listener = setupEndlessScroll()
        binding.rvTracks.adapter = adapter
        binding.rvTracks.addOnScrollListener(listener)
    }
}