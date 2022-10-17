package com.android.gameofthrones.data.source.local

import com.android.gameofthrones.data.local.dao.CharacterDao
import com.android.gameofthrones.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val characterDao: CharacterDao) : LocalDataSource {

    override fun getCharacterFromDb(): Flow<List<CharacterEntity>> = characterDao.get()
    override suspend fun getCharacterFromDb(id: Int): CharacterEntity = characterDao.get(id)

    override suspend fun insertCharacterToDb(entities: List<CharacterEntity>) = characterDao.insert(entities)
    override fun searchCharacter(search: String): Flow<List<CharacterEntity>> = characterDao.searchCharacter(search)
}
