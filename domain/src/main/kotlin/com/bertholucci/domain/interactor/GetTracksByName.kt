package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.TrackDomain
import com.bertholucci.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class GetTracksByName(private val repository: TrackRepository) :
    UseCase<Pair<String, Int>, List<TrackDomain>?>() {

    override fun executeUseCase(requestValues: Pair<String, Int>): Flow<List<TrackDomain>?> {
        return repository.getTracksByName(track = requestValues.first, page = requestValues.second)
    }
}