package com.bertholucci.domain.interactor

import com.bertholucci.domain.UseCase
import com.bertholucci.domain.model.MusicDomain
import com.bertholucci.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow

class GetTracksByName(private val repository: MusicRepository) :
    UseCase<Pair<String, Int>, List<MusicDomain>?>() {

    override fun executeUseCase(requestValues: Pair<String, Int>): Flow<List<MusicDomain>?> {
        return repository.getTracksByName(track = requestValues.first, page = requestValues.second)
    }
}