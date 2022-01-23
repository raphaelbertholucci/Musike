package com.bertholucci.data.model

import com.google.gson.annotations.SerializedName

data class MusicResults(@SerializedName("results") val results: MusicMatches) {
    fun getTracks() = results.matches.tracks
}

data class MusicMatches(@SerializedName("trackmatches") val matches: MusicTracks)

data class MusicTracks(@SerializedName("track") val tracks: List<MusicResponse>)

data class MusicResponse(
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: String,
    @SerializedName("listeners") val listeners: String,
    @SerializedName("url") val url: String,
    @SerializedName("image") val image: List<ImageResponse>
)

data class ImageResponse(
    @SerializedName("#text") val image: String,
    @SerializedName("size") val size: String
)