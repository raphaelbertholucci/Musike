package com.bertholucci.search.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.bertholucci.search.R

fun Fragment.showSnack(
    view: View? = activity?.getView(),
    @StringRes resId: Int,
    @ColorRes colorId: Int = R.color.color_white
) {
    activity?.showSnack(view = view, resId = resId, colorId = colorId)
}