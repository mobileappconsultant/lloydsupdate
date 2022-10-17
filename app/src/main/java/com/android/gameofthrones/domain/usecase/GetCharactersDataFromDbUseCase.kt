package com.android.gameofthrones.domain.usecase

import com.android.gameofthrones.data.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersDataFromDbUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend fun execute() = characterRepository.getCharactersFromDb()
}
