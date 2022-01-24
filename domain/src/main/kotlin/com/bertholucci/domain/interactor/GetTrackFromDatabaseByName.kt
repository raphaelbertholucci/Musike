package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.TrackDomain
import com.bertholucci.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class GetTrackFromDatabaseByName(private val repository: TrackRepository) :
    UseCase<Pair<String, String>, TrackDomain?>() {

    override fun executeUseCase(requestValues: Pair<String, String>): Flow<TrackDomain?> {
        return repository.getMusicFromDatabaseByID(
            track = requestValues.first,
            artist = requestValues.second
        )
    }
}