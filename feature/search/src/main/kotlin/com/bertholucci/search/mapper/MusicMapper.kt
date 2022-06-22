package com.bertholucci.search.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.domain.model.TrackDomain
import com.bertholucci.search.model.Track

class MusicMapper : BaseMapper<Track, TrackDomain> {

    override fun mapFromDomain(domain: TrackDomain): Track {
        return Track(
            name = domain.name,
            artist = domain.artist,
            listeners = domain.listeners,
            url = domain.url,
            image = domain.image.map { ImageMapper().mapFromDomain(it) }
        )
    }

    override fun mapToDomain(model: Track): TrackDomain {
        return TrackDomain(
            name = model.name,
            artist = model.artist,
            listeners = model.listeners,
            url = model.url,
            image = model.image.map { ImageMapper().mapToDomain(it) }
        )
    }

    fun mapFromDomainList(list: List<TrackDomain>?) = list?.map {
        mapFromDomain(it)
    } ?: emptyList()
}
