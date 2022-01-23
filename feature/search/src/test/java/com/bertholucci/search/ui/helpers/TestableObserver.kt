package com.bertholucci.search.ui.helpers

import androidx.lifecycle.Observer
import com.bertholucci.common.helpers.Response
import org.junit.Assert.assertEquals

class TestableObserver<T> : Observer<Response<T>> {

    private val history: MutableList<Response<T>> = mutableListOf()

    override fun onChanged(value: Response<T>) {
        history.add(value)
    }

    fun assertAllEmitted(values: List<T>) {
        assertEquals(values.count(), history.count())

        history.forEachIndexed { index, t ->
            assertEquals(values[index], t)
        }
    }
}