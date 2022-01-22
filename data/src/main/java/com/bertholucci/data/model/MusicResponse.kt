package com.bertholucci.data.model

import com.google.gson.annotations.SerializedName

class MusicResults(@SerializedName("results") val results: MusicMatches)

class MusicMatches(@SerializedName("trackmatches") val matches: MusicMatches)

class MusicTracks(@SerializedName("track") val tracks: List<MusicResponse>)

class MusicResponse(
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<ImageResponse>
)

class ImageResponse(
    @SerializedName("#text") val image: String,
    @SerializedName("size") val size: String
)