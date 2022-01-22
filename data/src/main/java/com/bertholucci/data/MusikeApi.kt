package com.bertholucci.data

import com.bertholucci.data.model.MusicResults
import retrofit2.http.GET
import retrofit2.http.Query

interface MusikeApi {
    @GET("/2.0/")
    suspend fun searchRestaurants(
        @Query("method") method: String,
        @Query("track") track: String
    ): MusicResults
}