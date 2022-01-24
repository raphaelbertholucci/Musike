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

fun ImageView.isFavorite(isFavorite: Boolean) {
    this.setImageResource(
        if (isFavorite) R.drawable.search_ic_heart_filled
        else R.drawable.search_ic_heart
    )
}