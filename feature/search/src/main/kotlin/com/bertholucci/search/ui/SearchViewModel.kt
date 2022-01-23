package com.bertholucci.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.common.extensions.defaultValue
import com.bertholucci.common.helpers.Response
import com.bertholucci.domain.interactor.GetMusicByName
import com.bertholucci.domain.model.MusicDomain
import com.bertholucci.search.mapper.MusicMapper
import com.bertholucci.search.model.Music
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

const val START_PAGE = 0

class SearchViewModel(private val getMusicByName: GetMusicByName) : ViewModel() {

    var page = MutableLiveData<Int>().defaultValue(START_PAGE)
    var popularityChoice = MutableLiveData<Boolean?>()

    private val _tracks = MutableLiveData<Response<List<Music>>>()
    val tracks: LiveData<Response<List<Music>>>
        get() = _tracks

    fun getTracksByName(name: String, page: Int = START_PAGE) {
        this.page.value = page
        getMusicByName(Pair(first = name, second = page))
            .onStart { _tracks.value = Response.Loading(true) }
            .onCompletion { _tracks.value = Response.Loading(false) }
            .map { _tracks.value = Response.Success(MusicMapper().mapFromDomainList(getList(it))) }
            .catch { _tracks.value = Response.Failure(it) }
            .launchIn(viewModelScope)
    }

    private fun getList(it: List<MusicDomain>?) =
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
}