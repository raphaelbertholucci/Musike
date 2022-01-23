package com.bertholucci.common.extensions

import androidx.lifecycle.MutableLiveData
import com.bertholucci.common.helpers.Response

fun <T : Any> MutableLiveData<T>.defaultValue(defaultValue: T, async: Boolean = false) =
    apply {
        if (value == null) {
            if (async) postValue(defaultValue)
        } else {
            value = defaultValue
        }
    }

fun <V> MutableLiveData<Response<V>>.showLoading() {
    value = Response.Loading(true)
}

fun <V> MutableLiveData<Response<V>>.hideLoading() {
    value = Response.Loading(false)
}

fun <V> MutableLiveData<Response<V>>.success(response: V) {
    value = Response.Success(response)
}

fun <V> MutableLiveData<Response<V>>.failure(error: Throwable) {
    value = Response.Failure(error)
}