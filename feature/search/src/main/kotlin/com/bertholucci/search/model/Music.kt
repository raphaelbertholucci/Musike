package com.bertholucci.search.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    val name: String,
    val artist: String,
    val listeners: String,
    val url: String,
    val image: List<Image>
) : Parcelable

@Parcelize
class Image(val image: String) : Parcelable