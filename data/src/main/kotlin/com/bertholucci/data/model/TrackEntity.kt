package com.bertholucci.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val artist: String,
    val listeners: String,
    val url: String,
    val image: String
)

@Entity(tableName = "images")
class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String
)