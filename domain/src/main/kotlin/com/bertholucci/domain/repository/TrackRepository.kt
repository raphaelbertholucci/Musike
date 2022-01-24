package com.bertholucci.domain.repository

import com.bertholucci.domain.model.TrackDomain
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    fun getTracksByName(track: String, page: Int = 0): Flow<List<TrackDomain>>

    fun getMusicFromDatabaseByID(track: String, artist: String): Flow<TrackDomain>
    fun insertTrack(track: TrackDomain): Flow<Unit>
    fun removeTrack(track: String, artist: String): Flow<Unit>
}