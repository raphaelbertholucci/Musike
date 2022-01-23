package com.bertholucci.search.extensions

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.bertholucci.search.RecyclerViewMatcher.Companion.withRecyclerView
import kotlin.test.assertTrue

inline fun <reified T : View> Int.checkRecyclerViewItem(
    position: Int,
    idItem: Int,
    crossinline assert: (T) -> Boolean
) {
    onView(withRecyclerView(this).atPositionOnView(position, idItem))
        .check { view, _ ->
            if (view is T) {
                assertTrue {
                    assert.invoke(view)
                }
            }
        }
}

fun Int.hasText(textId: Int) {
    onView(withId(this)).check(ViewAssertions.matches(ViewMatchers.withText(textId)))
}

fun Int.typeText(text: String) {
    onView(withId(this)).perform(ViewActions.click()).perform(ViewActions.typeText(text))
}

fun Int.click() {
    onView(withId(this)).perform(ViewActions.click())
}