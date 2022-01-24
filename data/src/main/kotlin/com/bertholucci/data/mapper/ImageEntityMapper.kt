package com.bertholucci.data.mapper

import com.bertholucci.common.base.BaseMapper
import com.bertholucci.data.model.ImageEntity
import com.bertholucci.domain.model.ImageDomain

class ImageEntityMapper : BaseMapper<ImageEntity, ImageDomain> {

    override fun mapFromDomain(domain: ImageDomain): ImageEntity {
        return ImageEntity(image = domain.image)
    }

    override fun mapToDomain(model: ImageEntity): ImageDomain {
        return ImageDomain(image = model.image)
    }
}