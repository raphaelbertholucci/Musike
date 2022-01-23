package com.bertholucci.data.repository

import com.bertholucci.data.MusikeApi
import com.bertholucci.data.mapper.MusicResponseMapper
import com.bertholucci.data.model.MusicResponse
import com.bertholucci.domain.model.MusicDomain
import com.bertholucci.domain.repository.MusicRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MusicRepositoryImpl(private val api: MusikeApi) : MusicRepository {

    override fun getTracksByName(track: String, page: Int): Flow<List<MusicDomain>> {
        return flow {
            val response = api.getTracksByName(
                method = "track.search",
                format = "json",
                limit = "20",
                track = track,
                page = page
            ).results.matches.tracks
            emit(map(response))
        }
    }

    private fun map(list: List<MusicResponse>) = MusicResponseMapper().mapToDomainList(list)
}