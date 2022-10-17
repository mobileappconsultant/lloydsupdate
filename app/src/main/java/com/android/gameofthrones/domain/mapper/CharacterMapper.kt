package com.android.gameofthrones.domain.mapper

import com.android.gameofthrones.data.local.entities.CharacterEntity
import com.android.gameofthrones.data.model.GOTApiResponseItem
import com.android.gameofthrones.domain.model.CharacterDomain
import com.android.gameofthrones.ui.model.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor() {
    fun mapToDomain(schema: GOTApiResponseItem): CharacterDomain = CharacterDomain(
        family = schema.family,
        id = schema.id,
        fullName = schema.fullName,
        imageUrl = schema.imageUrl,
        title = schema.title
    )

    fun mapToPresentation(domain: CharacterDomain): Character = Character(
        id = domain.id,
        family = domain.family,
        fullName = domain.fullName,
        imageUrl = domain.imageUrl,
        title = domain.title
    )

    fun mapToEntity(domain: CharacterDomain): CharacterEntity = CharacterEntity(
        family = domain.family,
        id = domain.id,
        fullName = domain.fullName,
        imageUrl = domain.imageUrl,
        title = domain.title
    )

    fun mapToDomain(entity: CharacterEntity): CharacterDomain = CharacterDomain(
        family = entity.family,
        id = entity.id,
        fullName = entity.fullName,
        imageUrl = entity.imageUrl,
        title = entity.title
    )
}
