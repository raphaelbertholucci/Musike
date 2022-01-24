package com.bertholucci.data.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.data.model.TrackEntity
import com.bertholucci.domain.model.ImageDomain
import com.bertholucci.domain.model.TrackDomain

class TrackEntityMapper : BaseMapper<TrackEntity, TrackDomain> {

    override fun mapFromDomain(domain: TrackDomain): TrackEntity {
        val images = domain.image
        return TrackEntity(
            name = domain.name,
            artist = domain.artist,
            listeners = domain.listeners,
            url = domain.url,
            image = if (images.isNotEmpty()) {
                images.last().image
            } else ""
        )
    }

    override fun mapToDomain(model: TrackEntity): TrackDomain {
        return TrackDomain(
            name = model.name,
            artist = model.artist,
            listeners = model.listeners,
            url = model.url,
            image = listOf(ImageDomain(image = model.image))
        )
    }
}