package com.bertholucci.search.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.common.extensions.defaultValue
import com.bertholucci.common.extensions.failure
import com.bertholucci.common.extensions.hideLoading
import com.bertholucci.common.extensions.showLoading
import com.bertholucci.common.extensions.success
import com.bertholucci.common.helpers.Response
import com.bertholucci.domain.interactor.GetTracksByName
import com.bertholucci.domain.model.TrackDomain
import com.bertholucci.search.mapper.MusicMapper
import com.bertholucci.search.model.Track
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

const val START_PAGE = 0

class SearchViewModel(private val getTracksByName: GetTracksByName) : ViewModel() {

    var page = MutableLiveData<Int>().defaultValue(START_PAGE)
    private var popularityChoice = MutableLiveData<Boolean?>()

    private val _tracks = MutableLiveData<Response<List<Track>>>()
    val tracks: LiveData<Response<List<Track>>>
        get() = _tracks

    fun getTracksByName(name: String, page: Int = START_PAGE) {
        this.page.value = page
        getTracksByName(Pair(first = name, second = page))
            .onStart { _tracks.showLoading() }
            .onCompletion { _tracks.hideLoading() }
            .map { _tracks.success(MusicMapper().mapFromDomainList(getList(it))) }
            .catch { _tracks.failure(it) }
            .launchIn(viewModelScope)
    }

    private fun getList(it: List<TrackDomain>?) =
        popularityChoice.value?.let { isCrescent ->
            if (isCrescent) {
                it?.sortedBy { music -> music.listeners.toInt() }
            } else {
                it?.sortedByDescending { music -> music.listeners.toInt() }
            }
        } ?: it

    fun updatePage(index: Int) {
        this.page.value = index
    }

    fun isFirstPage() = page.value == START_PAGE

    fun updatePopularityChoice(isCrescent: Boolean?) {
        popularityChoice.value = isCrescent
    }

    @VisibleForTesting
    fun getTracksByNameTest(name: String, page: Int = START_PAGE) {
        getTracksByName(Pair(first = name, second = page))
            .map { _tracks.success(MusicMapper().mapFromDomainList(getList(it))) }
            .launchIn(viewModelScope)
    }
}