package com.bertholucci.data.repository

import com.bertholucci.data.MusikeApi
import com.bertholucci.data.database.MusikeDao
import com.bertholucci.data.mapper.TrackEntityMapper
import com.bertholucci.data.mapper.TrackResponseMapper
import com.bertholucci.data.model.MusicResponse
import com.bertholucci.domain.model.TrackDomain
import com.bertholucci.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TrackRepositoryImpl(
    private val api: MusikeApi,
    private val dao: MusikeDao
) : TrackRepository {

    override fun getTracksByName(track: String, page: Int): Flow<List<TrackDomain>> {
        return flow {
            val response = api.getTracksByName(
                method = "track.search",
                format = "json",
                limit = "20",
                track = track,
                page = page
            ).getTracks()
            emit(map(response))
        }
    }

    override fun getMusicFromDatabaseByID(track: String, artist: String): Flow<TrackDomain> {
        return flow {
            emit(TrackEntityMapper().mapToDomain(dao.getTrackFromDatabaseByName(track, artist)))
        }
    }

    override fun insertTrack(track: TrackDomain): Flow<Unit> {
        return flow {
            emit(dao.insertTrack(TrackEntityMapper().mapFromDomain(track)))
        }
    }

    override fun removeTrack(track: String, artist: String): Flow<Unit> {
        return flow {
            emit(dao.removeTrack(track, artist))
        }
    }

    private fun map(list: List<MusicResponse>) = TrackResponseMapper().mapToDomainList(list)
}