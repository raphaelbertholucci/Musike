package com.bertholucci.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bertholucci.data.model.TrackEntity

@Dao
interface MusikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: TrackEntity)

    @Query("DELETE FROM tracks WHERE name=:name AND artist=:artist")
    suspend fun removeTrack(name: String, artist: String)

    @Query("SELECT * FROM tracks WHERE name=:name AND artist=:artist")
    suspend fun getTrackFromDatabaseByName(name: String, artist: String): TrackEntity
}