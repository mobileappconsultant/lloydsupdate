package com.android.gameofthrones.domain.mapper

import com.android.gameofthrones.data.local.entities.CharacterEntity
import com.android.gameofthrones.data.model.GOTApiResponseItem
import com.android.gameofthrones.domain.model.CharacterDomain
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterMapperTest {
    private val sut = CharacterMapper()

    private val mockedImageUrl = "mockUrl"
    private val mockedId = 98
    private val mockedFullName = "mockedCountryCode"
    private val mockedTitle = "mockedCity"
    private val mockedFamily = "mockedFamily"

    private val mockedCharacterSchema = mockk<GOTApiResponseItem>().apply {
        every { imageUrl } returns mockedImageUrl
        every { id } returns mockedId
        every { fullName } returns mockedFullName
        every { family } returns mockedFamily
        every { title } returns mockedTitle
    }

    private val mockedCharacterDomain = CharacterDomain(
        id = mockedId,
        family = mockedFamily,
        fullName = mockedFullName,
        imageUrl = mockedImageUrl,
        title = mockedTitle
    )

    private val mockedCharacterEntity = CharacterEntity(
        id = mockedId,
        family = mockedFamily,
        fullName = mockedFullName,
        imageUrl = mockedImageUrl,
        title = mockedTitle
    )

    @Test
    fun `test schema mapping`() {
        val actualResult = sut.mapToDomain(mockedCharacterSchema)
        assertEquals(mockedCharacterDomain, actualResult)
    }

    @Test
    fun `test domain mapping`() {
        val actualWeatherEntity = sut.mapToEntity(mockedCharacterDomain)
        val actualDomain = sut.mapToDomain(mockedCharacterEntity)
        assertEquals(mockedCharacterEntity, actualWeatherEntity)
        assertEquals(mockedCharacterDomain, actualDomain)
    }
}
