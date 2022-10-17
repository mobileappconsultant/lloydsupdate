package com.android.gameofthrones.domain.usecase

import com.android.gameofthrones.data.repository.CharacterRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    fun execute(search: String) = characterRepository.searchCharacter(search)
}