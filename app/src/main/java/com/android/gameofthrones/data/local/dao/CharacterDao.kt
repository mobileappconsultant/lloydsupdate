package com.android.gameofthrones.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.gameofthrones.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<CharacterEntity>)

    @Query("SELECT * FROM character_table")
    fun get(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM character_table WHERE id = :id")
    fun get(id : Int): CharacterEntity

    @Query("SELECT * FROM character_table WHERE fullName LIKE '%' || :search || '%' ")
    fun searchCharacter(search: String): Flow<List<CharacterEntity>>
}
