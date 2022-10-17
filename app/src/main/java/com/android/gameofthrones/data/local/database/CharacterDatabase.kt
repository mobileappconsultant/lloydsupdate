package com.android.gameofthrones.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.gameofthrones.data.local.dao.CharacterDao
import com.android.gameofthrones.data.local.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun weatherCityDao(): CharacterDao
}
