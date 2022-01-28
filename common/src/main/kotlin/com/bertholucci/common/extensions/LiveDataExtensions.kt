package com.bertholucci.common.extensions

import androidx.lifecycle.MutableLiveData
import com.bertholucci.common.helpers.Response
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

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

@ExperimentalCoroutinesApi
fun <T : Any> Flow<T>.response(
    liveData: MutableLiveData<Response<T>>,
    map: (T) -> Response<T> = { Response.Success(it) }
): Flow<Unit> {
    return this
        .onStart { liveData.showLoading() }
        .map { liveData.value = map(it) }
        .onCompletion { liveData.hideLoading() }
        .catch { liveData.value = Response.Failure(it) }
}