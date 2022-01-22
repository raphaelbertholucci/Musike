package com.bertholucci.data

import com.bertholucci.data.model.MusicResults
import retrofit2.http.GET
import retrofit2.http.Query

interface MusikeApi {
    @GET("/2.0/")
    suspend fun getTracksByName(
        @Query("method") method: String,
        @Query("format") format: String,
        @Query("limit") limit: String,
        @Query("track") track: String,
        @Query("page") page: Int
    ): MusicResults
}