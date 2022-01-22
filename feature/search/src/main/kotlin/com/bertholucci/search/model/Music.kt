package com.bertholucci.search.model

data class Music(
    val name: String,
    val artist: String,
    val listeners: String,
    val url: String,
    val image: List<Image>
)

class Image(val image: String, val size: String)