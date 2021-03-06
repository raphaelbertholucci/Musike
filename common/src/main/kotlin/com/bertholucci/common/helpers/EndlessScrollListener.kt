package com.bertholucci.common.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var page = 0
    private var loading = true
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var previousTotal = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading && (totalItemCount > previousTotal)) {
                loading = false
                previousTotal = totalItemCount
            }
            if (!loading && (totalItemCount) <= (firstVisibleItem + visibleItemCount)) {
                page++
                onLoadMore(page)
                loading = true
            }
        }
    }

    fun reset() {
        previousTotal = 0
    }

    abstract fun onLoadMore(page: Int)
}