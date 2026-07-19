package com.jetpack.compose.github.github.cruise.data.repository.favorites

import com.jetpack.compose.github.github.cruise.domain.model.FavoriteItem
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteType
import kotlinx.coroutines.flow.StateFlow

/**
 * Repository interface for managing favorites
 */
interface FavoritesRepository {

    /**
     * Get all favorites as a Flow
     */
    val favorites: StateFlow<List<FavoriteItem>>

    /**
     * Add item to favorites
     */
    suspend fun addFavorite(item: FavoriteItem)

    /**
     * Remove item from favorites
     */
    suspend fun removeFavorite(itemId: String, type: FavoriteType)

    /**
     * Check if item is favorited
     */
    suspend fun isFavorite(itemId: String, type: FavoriteType): Boolean

    /**
     * Toggle favorite status
     */
    suspend fun toggleFavorite(item: FavoriteItem)

    /**
     * Clear all favorites
     */
    suspend fun clearFavorites()
}
