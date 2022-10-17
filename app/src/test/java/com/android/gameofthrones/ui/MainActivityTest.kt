package com.android.gameofthrones.ui

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.IdlingRegistry
import com.android.gameofthrones.CoroutineTestRule
import com.android.gameofthrones.dispatchers.SuccessDispatcher
import com.android.gameofthrones.ui.navigation.AppNavigation
import com.android.gameofthrones.utils.Constants.CARD_ITEM_TEXT_TAG
import com.android.gameofthrones.utils.Constants.DETAILS_TEST_TAG
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.Thread.sleep
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.O_MR1],
    application = HiltTestApplication::class
)
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule(order = 2)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private val mockWebServer = MockWebServer()

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @Before
    fun setup() {
        mockWebServer.start(8080)

        hiltTestRule.inject()
        IdlingRegistry.getInstance().register((AndroidXIdlingResource.asAndroidX(OkHttp3IdlingResource.create("okhttp", okHttpClient))))
    }

    @Test
    fun `given when application is launched and the weather data is available, then it should show the list`() = runTest {
        mockWebServer.dispatcher = SuccessDispatcher()
        composeTestRule.apply {
            setContent {
                AppNavigation()
            }
            sleep(5000)
            onAllNodesWithTag(CARD_ITEM_TEXT_TAG).onFirst().assertExists()
            onAllNodesWithTag(CARD_ITEM_TEXT_TAG).onFirst().performClick()
            sleep(5000)
            onNodeWithTag(DETAILS_TEST_TAG).assertIsDisplayed()
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}
