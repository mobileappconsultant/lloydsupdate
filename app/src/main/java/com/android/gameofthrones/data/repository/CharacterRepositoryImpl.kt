package com.android.gameofthrones.data.repository

import com.android.gameofthrones.data.source.local.LocalDataSource
import com.android.gameofthrones.data.source.remote.RemoteDataSource
import com.android.gameofthrones.domain.mapper.CharacterMapper
import com.android.gameofthrones.domain.model.CharacterDomain
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl @Inject constructor(
    private val characterMapper: CharacterMapper,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : CharacterRepository {
    override fun getCharactersFromDb(): Flow<List<CharacterDomain>> =
        localDataSource.getCharacterFromDb().map {
            it.map { entity ->
                characterMapper.mapToDomain(entity)
            }
        }

    override suspend fun insertCharactersToDb(items: List<CharacterDomain>) =
        localDataSource.insertCharacterToDb(
            items.map {
                characterMapper.mapToEntity(it)
            }
        )

    override suspend fun getCharactersFromApi(): List<CharacterDomain> =
        remoteDataSource.getAllCharacters().map {
            characterMapper.mapToDomain(it)
        }

    override fun searchCharacter(search: String): Flow<List<CharacterDomain>> =
        localDataSource.searchCharacter(search).map {
            it.map { entity ->
                characterMapper.mapToDomain(entity)
            }
        }

}
