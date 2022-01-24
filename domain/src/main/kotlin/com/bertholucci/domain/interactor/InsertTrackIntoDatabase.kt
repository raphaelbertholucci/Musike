package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.TrackDomain
import com.bertholucci.domain.repository.TrackRepository
import kotlinx.coroutines.flow.Flow

class InsertTrackIntoDatabase(private val repository: TrackRepository) :
    UseCase<TrackDomain, Unit>() {

    override fun executeUseCase(requestValues: TrackDomain): Flow<Unit> {
        return repository.insertTrack(requestValues)
    }
}