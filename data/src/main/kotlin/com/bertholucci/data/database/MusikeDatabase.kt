package com.bertholucci.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bertholucci.data.model.TrackEntity

@Database(entities = [TrackEntity::class], version = 1)
abstract class MusikeDatabase : RoomDatabase() {
    abstract fun musikeDao(): MusikeDao
}