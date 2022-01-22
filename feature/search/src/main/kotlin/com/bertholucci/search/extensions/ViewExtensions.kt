package com.bertholucci.search.extensions

import android.widget.ImageView
import coil.load
import com.bertholucci.search.R

fun ImageView.loadFromUrl(image: String?) {
    this.load(image) {
        crossfade(true)
        placeholder(R.drawable.img_music_placeholder)
    }
}