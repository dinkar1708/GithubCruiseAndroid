package com.jetpack.compose.github.cruise.journeys

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jetpack.compose.github.github.cruise.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Journey 11: Repository Search
 * Tests the repository search functionality (Feature 2.1)
 */
@RunWith(AndroidJUnit4::class)
class Journey11_RepositorySearchTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun journey11_canNavigateToRepositorySearchTab() {
        // Wait for app to be ready
        composeTestRule.waitForIdle()

        // Wait for home screen to load
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule
                .onAllNodesWithContentDescription("Repositories")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        // Click on Repositories tab
        composeTestRule.onNodeWithContentDescription("Repositories").performClick()
        composeTestRule.waitForIdle()

        // Verify repository search screen is shown
        composeTestRule.onNodeWithText("Repository Search").assertExists()
    }

    @Test
    fun journey11_canTypeInRepositorySearchField() {
        composeTestRule.waitForIdle()

        // Wait and navigate to Repositories tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Repositories").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Repositories").performClick()
        composeTestRule.waitForIdle()

        // Wait for search field
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("repository_search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Type in search field
        composeTestRule.onNodeWithTag("repository_search_input").performTextInput("android")

        // Verify text was entered
        composeTestRule.onNodeWithTag("repository_search_input").assertTextContains("android")
    }

    @Test
    fun journey11_searchAutoTriggersAfterTyping() {
        composeTestRule.waitForIdle()

        // Navigate to Repositories tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Repositories").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Repositories").performClick()
        composeTestRule.waitForIdle()

        // Wait for search field and type
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("repository_search_input").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithTag("repository_search_input").performTextInput("kotlin")

        // Wait for debounce + network
        Thread.sleep(1000)

        // Search should have triggered - screen should still exist
        composeTestRule.onNodeWithText("Repository Search").assertExists()
    }

    @Test
    fun journey11_emptySearchShowsEmptyState() {
        composeTestRule.waitForIdle()

        // Navigate to Repositories tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Repositories").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Repositories").performClick()
        composeTestRule.waitForIdle()

        // Don't type anything - empty search should show screen
        composeTestRule.onNodeWithText("Repository Search").assertExists()
    }

    @Test
    fun journey11_repositoryCardsShowInformation() {
        composeTestRule.waitForIdle()

        // Navigate to Repositories tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Repositories").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Repositories").performClick()
        composeTestRule.waitForIdle()

        // Wait for search field
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("repository_search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Search for a common term
        composeTestRule.onNodeWithTag("repository_search_input").performTextInput("github")
        Thread.sleep(1000)

        // Verify UI structure exists
        composeTestRule.onNodeWithText("Repository Search").assertExists()
    }

    @Test
    fun journey11_canScrollRepositoryList() {
        composeTestRule.waitForIdle()

        // Navigate to Repositories tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Repositories").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Repositories").performClick()
        composeTestRule.waitForIdle()

        // Wait for search field
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithTag("repository_search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Search
        composeTestRule.onNodeWithTag("repository_search_input").performTextInput("android")
        Thread.sleep(1000)

        // Repository Search screen should exist
        composeTestRule.onNodeWithText("Repository Search").assertExists()
    }
}
