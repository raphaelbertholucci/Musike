package com.bertholucci.domain.model

data class MusicDomain(
    val name: String = "",
    val artist: String = "",
    val listeners: String = "",
    val url: String = "",
    val image: List<ImageDomain> = emptyList()
)

data class ImageDomain(
    val image: String = "",
    val size: String = ""
)