package com.android.gameofthrones.ui.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import coil.annotation.ExperimentalCoilApi
import com.android.gameofthrones.ui.model.Character
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalCoilApi
@RunWith(RobolectricTestRunner::class)
class SearchListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockedName = "Jon"
    private val mockedName2 = "Snow"

    private val mockedSearchItem = Character(
        id = 123,
        family = "mockedFamily",
        fullName = mockedName,
        imageUrl = "mockedImageUrl",
        title = "mockedTitle"
    )

    private val mockedSearchItem2 = Character(
        id = 13,
        family = "mockedFamily",
        fullName = mockedName2,
        imageUrl = "mockedImageUrl",
        title = "mockedTitle"
    )

    @Test
    fun `given WeatherList Composable when Items is available, then the correct data should be displayed`() {
        val searchItems = listOf(mockedSearchItem, mockedSearchItem2)

        composeTestRule.setContent {
            CharacterList(
                searchItems,
                onItemSelect = {
                }
            )
        }
        composeTestRule.onNodeWithText(mockedName).assertIsDisplayed()
        composeTestRule.onNodeWithText(mockedName2).assertIsDisplayed()
    }
}
