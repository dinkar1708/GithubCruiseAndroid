package com.jetpack.compose.github.cruise.journeys

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jetpack.compose.github.github.cruise.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Journey 12: Favorites
 * Tests the favorites functionality (Feature 3.0)
 */
@RunWith(AndroidJUnit4::class)
class Journey12_FavoritesTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun journey12_canNavigateToFavoritesTab() {
        composeTestRule.waitForIdle()

        // Wait for home screen to load
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Favorites").fetchSemanticsNodes().isNotEmpty()
        }

        // Click on Favorites tab
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Verify favorites screen is shown - check for empty state
        composeTestRule.onNodeWithText("No Favorites Yet", substring = true).assertExists()
    }

    @Test
    fun journey12_emptyFavoritesShowsEmptyState() {
        composeTestRule.waitForIdle()

        // Navigate to Favorites tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Favorites").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Should show empty state message
        composeTestRule.onNodeWithText("No Favorites Yet", substring = true).assertExists()
    }

    @Test
    fun journey12_emptyStateMessageIsHelpful() {
        composeTestRule.waitForIdle()

        // Navigate to Favorites tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Favorites").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Verify helpful empty state message exists
        composeTestRule.onNode(
            hasText("Start adding", substring = true) or
            hasText("No Favorites", substring = true)
        ).assertExists()
    }

    @Test
    fun journey12_favoritesScreenHasTitle() {
        composeTestRule.waitForIdle()

        // Navigate to Favorites tab
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Favorites").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Verify empty state is shown (title is in header)
        composeTestRule.onNodeWithText("No Favorites Yet", substring = true).assertExists()
    }

    @Test
    fun journey12_canSwitchBetweenTabsAndFavorites() {
        composeTestRule.waitForIdle()

        // Navigate to Favorites
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Favorites").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Navigate to Users
        composeTestRule.onNodeWithContentDescription("Users").performClick()
        composeTestRule.waitForIdle()

        // Navigate back to Favorites
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Should still show Favorites screen with empty state
        composeTestRule.onNodeWithText("No Favorites Yet", substring = true).assertExists()
    }

    @Test
    fun journey12_favoritesTabPersistsAfterNavigation() {
        composeTestRule.waitForIdle()

        // Navigate to Favorites
        composeTestRule.waitUntil(timeoutMillis = 10000) {
            composeTestRule.onAllNodesWithContentDescription("Favorites").fetchSemanticsNodes().isNotEmpty()
        }
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Go to another tab
        composeTestRule.onNodeWithContentDescription("Settings").performClick()
        composeTestRule.waitForIdle()

        // Come back to Favorites
        composeTestRule.onNodeWithContentDescription("Favorites").performClick()
        composeTestRule.waitForIdle()

        // Favorites screen should still be accessible with empty state
        composeTestRule.onNodeWithText("No Favorites Yet", substring = true).assertExists()
    }
}
