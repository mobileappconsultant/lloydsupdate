package com.android.gameofthrones.ui.screens.viewmodel

import com.android.gameofthrones.CoroutineTestRule
import com.android.gameofthrones.dispatchers.TestDispatcherProvider
import com.android.gameofthrones.domain.mapper.CharacterMapper
import com.android.gameofthrones.domain.usecase.GetAllCharactersFromApiUseCase
import com.android.gameofthrones.domain.usecase.GetCharactersDataFromDbUseCase
import com.android.gameofthrones.domain.usecase.InsertCharactersDataToDbUseCase
import com.android.gameofthrones.domain.usecase.SearchUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var sut: HomeViewModel
    private val weatherMapper = CharacterMapper()
    private val searchQuery = "searchQuery"
    private val getAllCharactersDataFromApiUseCase = mockk<GetAllCharactersFromApiUseCase>().apply {
        coEvery { execute() } returns listOf()
    }
    private val insertCharactersToDbUseCase = mockk<InsertCharactersDataToDbUseCase>().apply {
        coEvery { execute(any()) } returns Unit
    }
    private val getDataFromDbUseCase = mockk<GetCharactersDataFromDbUseCase>().apply {
        coEvery { execute() } returns flowOf()
    }
    private val searchUseCase = mockk<SearchUseCase>().apply {
        coEvery { execute(searchQuery) } returns flowOf()
    }

    private val coroutineProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        sut = HomeViewModel(
            weatherMapper,
            getAllCharactersDataFromApiUseCase,
            insertCharactersDataToDbUseCase = insertCharactersToDbUseCase,
            getCharactersDataFromDbUseCase = getDataFromDbUseCase,
            searchUseCase = searchUseCase,
            dispatchers = coroutineProvider
        )
    }

    @Test
    fun `given when viewmodel is initialized, verify that all required usecases is executed`() =
        runTest {

            coVerifySequence {
                getAllCharactersDataFromApiUseCase.execute()
                insertCharactersToDbUseCase.execute(listOf())
                getDataFromDbUseCase.execute()
            }
        }

    @Test
    fun `given when searchCity is called, verify that SearchCityUseCase is executed`() = runTest {
        sut.search(searchQuery)
        coVerify {
            searchUseCase.execute(searchQuery)
        }
    }
}
