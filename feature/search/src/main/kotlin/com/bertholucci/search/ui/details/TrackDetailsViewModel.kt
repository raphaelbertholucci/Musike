package com.bertholucci.search.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bertholucci.common.extensions.defaultValue
import com.bertholucci.domain.interactor.GetTrackFromDatabaseByName
import com.bertholucci.domain.interactor.InsertTrackIntoDatabase
import com.bertholucci.domain.interactor.RemoveTrackFromDatabase
import com.bertholucci.search.mapper.MusicMapper
import com.bertholucci.search.model.Track
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class TrackDetailsViewModel(
    private val getTrackFromDatabaseByName: GetTrackFromDatabaseByName,
    private val insertTrackIntoDatabase: InsertTrackIntoDatabase,
    private val removeTrackFromDatabase: RemoveTrackFromDatabase,
    song: Track
) : ViewModel() {

    val track = MutableLiveData<Track>().defaultValue(song, async = true)
    val isFavorite = MutableLiveData<Boolean>()

    init {
        getTrackFromDBByName(song.name, song.artist)
    }

    fun updateTrackState() {
        track.value?.let {
            if (it.isFavorite) removeTrackFromDB(it)
            else insertTrackIntoDB(it)
        }
    }

    private fun updateTrack(isFavorite: Boolean) {
        track.value?.let {
            track.value = it.copy(isFavorite = isFavorite)
        }
    }

    private fun getTrackFromDBByName(name: String, artist: String) {
        getTrackFromDatabaseByName(Pair(name, artist))
            .map { updateTrack(true) }
            .catch { updateTrack(false) }
            .launchIn(viewModelScope)
    }

    private fun insertTrackIntoDB(track: Track) {
        insertTrackIntoDatabase(MusicMapper().mapToDomain(track))
            .map {
                isFavorite.value = true
                updateTrack(true)
            }
            .launchIn(viewModelScope)
    }

    private fun removeTrackFromDB(track: Track) {
        removeTrackFromDatabase(Pair(track.name, track.artist))
            .map {
                isFavorite.value = false
                updateTrack(false)
            }
            .launchIn(viewModelScope)
    }
}