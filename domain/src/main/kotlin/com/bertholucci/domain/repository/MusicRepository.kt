package com.bertholucci.domain.repository

import com.bertholucci.domain.model.MusicDomain
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getTracksByName(track: String, page: Int = 1): Flow<List<MusicDomain>>
}