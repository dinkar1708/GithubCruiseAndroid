package com.jetpack.compose.github.github.cruise.ui.features.favorites

import com.jetpack.compose.github.github.cruise.domain.model.FavoriteItem

/**
 * UI state for Favorites screen
 */
data class FavoritesState(
    val favorites: List<FavoriteItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
