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
        // Wait for splash screen to finish
        Thread.sleep(3000)

        // Verify we can see the "Favorites" tab
        composeTestRule.onNodeWithText("Favorites").assertExists()

        // Click on Favorites tab
        composeTestRule.onNodeWithText("Favorites").performClick()

        // Verify favorites screen is shown
        composeTestRule.onNodeWithText("Favorites").assertExists()
    }

    @Test
    fun journey12_emptyFavoritesShowsEmptyState() {
        Thread.sleep(3000)

        // Navigate to Favorites tab
        composeTestRule.onNodeWithText("Favorites").performClick()
        Thread.sleep(500)

        // Should show empty state message
        composeTestRule.onNodeWithText("No Favorites Yet", substring = true).assertExists()
    }

    @Test
    fun journey12_emptyStateMessageIsHelpful() {
        Thread.sleep(3000)

        // Navigate to Favorites tab
        composeTestRule.onNodeWithText("Favorites").performClick()
        Thread.sleep(500)

        // Verify helpful empty state message exists
        composeTestRule.onNode(
            hasText("Start adding", substring = true) or
            hasText("No Favorites", substring = true)
        ).assertExists()
    }

    @Test
    fun journey12_favoritesScreenHasTitle() {
        Thread.sleep(3000)

        // Navigate to Favorites tab
        composeTestRule.onNodeWithText("Favorites").performClick()
        Thread.sleep(500)

        // Verify title exists in header
        composeTestRule.onNodeWithText("Favorites").assertExists()
    }

    @Test
    fun journey12_canSwitchBetweenTabsAndFavorites() {
        Thread.sleep(3000)

        // Navigate to Favorites
        composeTestRule.onNodeWithText("Favorites").performClick()
        Thread.sleep(500)

        // Navigate to Users
        composeTestRule.onNodeWithText("Users").performClick()
        Thread.sleep(500)

        // Navigate back to Favorites
        composeTestRule.onNodeWithText("Favorites").performClick()
        Thread.sleep(500)

        // Should still show Favorites screen
        composeTestRule.onNodeWithText("Favorites").assertExists()
    }

    @Test
    fun journey12_favoritesTabPersistsAfterNavigation() {
        Thread.sleep(3000)

        // Navigate to Favorites
        composeTestRule.onNodeWithText("Favorites").performClick()
        Thread.sleep(500)

        // Go to another tab
        composeTestRule.onNodeWithText("Settings").performClick()
        Thread.sleep(500)

        // Come back to Favorites
        composeTestRule.onNodeWithText("Favorites").performClick()
        Thread.sleep(500)

        // Favorites screen should still be accessible
        composeTestRule.onNodeWithText("Favorites").assertExists()
    }
}
