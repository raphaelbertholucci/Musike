package com.bertholucci.data.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.data.model.MusicResponse
import com.bertholucci.domain.model.TrackDomain

class TrackResponseMapper : BaseMapper<MusicResponse, TrackDomain> {

    override fun mapFromDomain(domain: TrackDomain): MusicResponse {
        throw UnsupportedOperationException("Unsupported Operation")
    }

    override fun mapToDomain(model: MusicResponse): TrackDomain {
        return TrackDomain(
            name = model.name,
            artist = model.artist,
            listeners = model.listeners,
            url = model.url,
            image = model.image.map { ImageResponseMapper().mapToDomain(it) }
        )
    }

    fun mapToDomainList(list: List<MusicResponse>): List<TrackDomain> = list.map {
        TrackResponseMapper().mapToDomain(it)
    }
}