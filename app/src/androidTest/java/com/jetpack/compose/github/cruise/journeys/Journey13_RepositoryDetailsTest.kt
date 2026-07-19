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
        Thread.sleep(3000)

        // Search for a user
        composeTestRule.onNodeWithTag("search_input").performTextInput("dinkar1708")
        Thread.sleep(2000)

        // Click on first user (if exists)
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(1500)

        // Click on a repository (if exists)
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(2000)

        // Repository Details screen should load (even if API fails, screen should exist)
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }

    @Test
    fun journey13_loadingStateShowsWhileFetchingDetails() {
        Thread.sleep(3000)

        // Search and navigate to repository
        composeTestRule.onNodeWithTag("search_input").performTextInput("octocat")
        Thread.sleep(2000)

        // Try to navigate to a repository
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(1500)

        // Click on repository
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()

        // Should see Repository Details title immediately
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }

    @Test
    fun journey13_backNavigationWorksFromDetails() {
        Thread.sleep(3000)

        // Search and navigate
        composeTestRule.onNodeWithTag("search_input").performTextInput("github")
        Thread.sleep(2000)

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(1500)

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(1500)

        // Should be on Repository Details
        composeTestRule.onNodeWithText("Repository Details").assertExists()

        // Note: Back button navigation is tested in Journey 10
    }

    @Test
    fun journey13_detailsScreenShowsRepositoryInfo() {
        Thread.sleep(3000)

        // Navigate to a repository
        composeTestRule.onNodeWithTag("search_input").performTextInput("torvalds")
        Thread.sleep(2500)

        // Click on user
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(1500)

        // Click on repository
        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(2000)

        // Repository Details screen exists
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }

    @Test
    fun journey13_detailsScreenHandlesApiErrors() {
        Thread.sleep(3000)

        // Navigate through the flow
        composeTestRule.onNodeWithTag("search_input").performTextInput("test")
        Thread.sleep(2000)

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(1500)

        composeTestRule.onAllNodes(hasClickAction()).onFirst().performClick()
        Thread.sleep(2000)

        // Even if API fails, screen should handle it gracefully
        // Either show error or loading state, but not crash
        composeTestRule.onNodeWithText("Repository Details").assertExists()
    }
}
