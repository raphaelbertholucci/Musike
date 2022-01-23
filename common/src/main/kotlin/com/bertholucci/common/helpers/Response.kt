package com.bertholucci.common.helpers

sealed class Response<out V> {
    data class Success<out V>(val value: V) : Response<V>()
    data class Failure(val error: Throwable) : Response<Nothing>()
    data class Loading(val loading: Boolean) : Response<Nothing>()

    private fun <V> success(value: V): Response<V> = Success(value)
    private fun failure(value: Throwable): Response<Nothing> = Failure(value)
    private fun loading(value: Boolean): Response<Nothing> = Loading(value)

    fun <V> response(action: () -> V): Response<V> =
        try {
            success(action())
        } catch (e: Exception) {
            failure(e)
        }
}

inline fun <V, A> Response<V>.fold(
    error: (Throwable) -> A,
    loading: (Boolean) -> A,
    success: (V) -> A
): A = when (this) {
    is Response.Failure -> error(this.error)
    is Response.Success -> success(this.value)
    is Response.Loading -> loading(this.loading)
}