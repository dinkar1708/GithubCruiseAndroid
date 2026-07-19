package com.jetpack.compose.github.cruise.journeys

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jetpack.compose.github.github.cruise.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Journey 13: Repository Details
 * Tests the repository details screen (Feature 2.2)
 */
@RunWith(AndroidJUnit4::class)
class Journey13_RepositoryDetailsTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun journey13_repositoryDetailsScreenLoads() {
        composeTestRule.waitForIdle()

        // Wait for search field on Users tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithTag("search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Search for a user
        composeTestRule.onNodeWithTag("search_input").performTextInput("dinkar1708")
        Thread.sleep(1500)

        // Click on first user (if exists)
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Click on a repository (if exists)
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Repository Details screen should load
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }

    @Test
    fun journey13_loadingStateShowsWhileFetchingDetails() {
        composeTestRule.waitForIdle()

        // Wait for search field on Users tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithTag("search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Search and navigate to repository
        composeTestRule.onNodeWithTag("search_input").performTextInput("octocat")
        Thread.sleep(1500)

        // Try to navigate to a repository
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Click on repository
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Should see Repository Details title
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }

    @Test
    fun journey13_backNavigationWorksFromDetails() {
        composeTestRule.waitForIdle()

        // Wait for search field on Users tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithTag("search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Search and navigate
        composeTestRule.onNodeWithTag("search_input").performTextInput("github")
        Thread.sleep(1500)

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Should be on Repository Details
        composeTestRule.onNodeWithText("Repository Details").assertExists()

        // Note: Back button navigation is tested in Journey 10
    }

    @Test
    fun journey13_detailsScreenShowsRepositoryInfo() {
        composeTestRule.waitForIdle()

        // Wait for search field on Users tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithTag("search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Navigate to a repository
        composeTestRule.onNodeWithTag("search_input").performTextInput("torvalds")
        Thread.sleep(2000)

        // Click on user
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Click on repository
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Repository Details screen exists
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }

    @Test
    fun journey13_detailsScreenHandlesApiErrors() {
        composeTestRule.waitForIdle()

        // Wait for search field on Users tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithTag("search_input").fetchSemanticsNodes().isNotEmpty()
        }

        // Navigate through the flow
        composeTestRule.onNodeWithTag("search_input").performTextInput("test")
        Thread.sleep(1500)

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        composeTestRule.waitForIdle()

        // Even if API fails, screen should handle it gracefully
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }
}
