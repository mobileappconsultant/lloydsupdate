package com.android.gameofthrones.domain.usecase

import com.android.gameofthrones.data.repository.CharacterRepository
import com.android.gameofthrones.domain.model.CharacterDomain
import javax.inject.Inject

class InsertCharactersDataToDbUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend fun execute(items: List<CharacterDomain>) = characterRepository.insertCharactersToDb(items)
}
