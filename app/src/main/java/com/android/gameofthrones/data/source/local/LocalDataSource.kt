package com.android.gameofthrones.data.source.local

import com.android.gameofthrones.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getCharacterFromDb(): Flow<List<CharacterEntity>>
    suspend fun insertCharacterToDb(entities: List<CharacterEntity>)
    suspend fun getCharacterFromDb(id: Int) : CharacterEntity
    fun searchCharacter(search : String): Flow<List<CharacterEntity>>
}
