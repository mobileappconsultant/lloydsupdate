package com.android.gameofthrones.data.repository

import com.android.gameofthrones.domain.model.CharacterDomain
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharactersFromDb(): Flow<List<CharacterDomain>>
    suspend fun insertCharactersToDb(items: List<CharacterDomain>)
    suspend fun getCharactersFromApi(): List<CharacterDomain>
    fun searchCharacter(search: String): Flow<List<CharacterDomain>>

}
