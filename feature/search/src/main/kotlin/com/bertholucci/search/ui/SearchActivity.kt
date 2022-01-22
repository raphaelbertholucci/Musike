package com.bertholucci.search.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.bertholucci.common.base.BaseActivity
import com.bertholucci.common.helpers.EndlessScrollListener
import com.bertholucci.common.helpers.fold
import com.bertholucci.search.databinding.SearchActivityBinding
import com.bertholucci.search.model.Music
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<SearchActivityBinding>() {

    private val viewModel: SearchViewModel by viewModel()

    private val adapter = SearchAdapter()

    override fun getViewBinding() = SearchActivityBinding.inflate(LayoutInflater.from(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addObservers()
        addListeners()
        setupUI()
    }

    private fun addObservers() {
        viewModel.tracks.observe(this) { response ->
            response.fold(
                error = ::handleError,
                loading = ::handleLoading,
                success = ::handleSuccess
            )
        }
    }

    private fun handleSuccess(list: List<Music>) {
        adapter.isLoading = false
        when {
            list.isEmpty() && viewModel.isFirstPage() -> {
                display(empty = true)
            }
            viewModel.isFirstPage() && list.size < 20 -> {
                adapter.isFullyLoaded = true
                display(content = true)
            }
            viewModel.isFirstPage() && list.size == 20 -> {
                adapter.isFullyLoaded = false
                display(content = true)
            }
            viewModel.isFirstPage().not() -> {
                adapter.isFullyLoaded = false
                display(content = true)
            }
        }
        adapter.updateList(list, viewModel.page)
    }

    private fun handleLoading(loading: Boolean) {
        if (loading && viewModel.isFirstPage()) {
            display(loading = true)
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERROR", throwable.message, throwable)
        display(error = true)
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
            it?.let { text ->
                if (text.length > 3) {
                    viewModel.getTracksByName(text.toString())
                }
            }
        }
    }

    private fun setupEndlessScroll(layoutManager: LinearLayoutManager) {
        val endlessScrollListener = object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemCount: Int) {
                adapter.isLoading = true
                viewModel.updatePage(page)
                viewModel.getTracksByName(binding.etSearch.text.toString(), page)
            }
        }
        binding.rvTracks.addOnScrollListener(endlessScrollListener)
    }

    private fun setupUI() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvTracks.layoutManager = layoutManager
        binding.rvTracks.adapter = adapter
        setupEndlessScroll(layoutManager)
    }
}