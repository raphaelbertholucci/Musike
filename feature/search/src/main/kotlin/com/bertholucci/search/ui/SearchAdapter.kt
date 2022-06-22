package com.bertholucci.search.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bertholucci.search.R
import com.bertholucci.search.model.Track

class SearchAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isFullyLoaded = false

    private val viewTypeItem = 0
    private val viewTypeLoading = 1

    var onClick: ((Track) -> Unit)? = null
    private val list: MutableList<Track> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(tracks: List<Track>, page: Int?) {
        if (page == 0) {
            list.clear()
        }
        list.addAll(tracks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == viewTypeItem) {
            SearchViewHolder(
                itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.search_item_music, parent, false),
                onClick = onClick
            )
        } else {
            LoadingViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.search_item_loading, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            (holder is SearchViewHolder) -> holder.bind(list[position])
            (holder is LoadingViewHolder) -> holder.bind(isFullyLoaded)
        }
    }

    override fun getItemCount(): Int =
        if (list.isNotEmpty()) list.size + 1
        else 0

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) viewTypeLoading else viewTypeItem
    }

    override fun getItemId(position: Int): Long {
        return if (getItemViewType(position) == viewTypeItem) position.toLong()
        else -1
    }
}
