package com.android.gameofthrones.domain.usecase

import com.android.gameofthrones.data.repository.CharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchUseCaseTest {
    private lateinit var sut: SearchUseCase
    private val characterRepository = mockk<CharacterRepository>()

    @Test
    fun `given query, when execute is called, then githubSearchRepository searchUser should be called`() =
        runTest {
            sut = SearchUseCase(characterRepository)
            val query = "searchQuery"
            coEvery {
                characterRepository.searchCharacter(query)
            } returns flowOf()

            sut.execute(query)

            coVerify {
                characterRepository.searchCharacter(query)
            }
        }
}
