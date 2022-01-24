package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class RemoveTrackFromDatabase(private val repository: TrackRepository) :
    UseCase<Pair<String, String>, Unit>() {

    override fun executeUseCase(requestValues: Pair<String, String>): Flow<Unit> {
        return repository.removeTrack(track = requestValues.first, artist = requestValues.second)
    }
}