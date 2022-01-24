package com.bertholucci.search.extensions

import android.app.Activity
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.bertholucci.search.R
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnack(
    @StringRes resId: Int,
    view: View? = getView(),
    @ColorRes colorId: Int = R.color.color_white
) {
    val snackBar = Snackbar.make(view ?: getView(), resId, Snackbar.LENGTH_LONG).apply {
        this.view.setBackgroundColor(ContextCompat.getColor(this@showSnack, colorId))
    }
    snackBar.show()
}

fun Activity.getView(): View = findViewById(android.R.id.content)