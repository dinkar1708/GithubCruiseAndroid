package com.jetpack.compose.github.github.cruise.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

/**
 * Manager for favorites preferences
 */
class FavoritesPreferences(
    context: Context,
    private val moshi: Moshi
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val adapter = moshi.adapter<List<FavoriteItem>>(
        Types.newParameterizedType(List::class.java, FavoriteItem::class.java)
    )

    private val _favorites = MutableStateFlow(loadFavorites())
    val favorites: StateFlow<List<FavoriteItem>> = _favorites.asStateFlow()

    /**
     * Add item to favorites
     */
    fun addFavorite(item: FavoriteItem) {
        val currentFavorites = _favorites.value.toMutableList()
        if (currentFavorites.none { it.id == item.id && it.type == item.type }) {
            currentFavorites.add(0, item) // Add to the beginning
            saveFavorites(currentFavorites)
            _favorites.value = currentFavorites
        }
    }

    /**
     * Remove item from favorites
     */
    fun removeFavorite(itemId: String, type: com.jetpack.compose.github.github.cruise.domain.model.FavoriteType) {
        val currentFavorites = _favorites.value.toMutableList()
        currentFavorites.removeAll { it.id == itemId && it.type == type }
        saveFavorites(currentFavorites)
        _favorites.value = currentFavorites
    }

    /**
     * Check if item is favorited
     */
    fun isFavorite(itemId: String, type: com.jetpack.compose.github.github.cruise.domain.model.FavoriteType): Boolean {
        return _favorites.value.any { it.id == itemId && it.type == type }
    }

    /**
     * Clear all favorites
     */
    fun clearFavorites() {
        saveFavorites(emptyList())
        _favorites.value = emptyList()
    }

    /**
     * Load favorites from SharedPreferences
     */
    private fun loadFavorites(): List<FavoriteItem> {
        return try {
            val json = prefs.getString(KEY_FAVORITES, null)
            if (json != null) {
                adapter.fromJson(json) ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Timber.e(e, "Error loading favorites")
            emptyList()
        }
    }

    /**
     * Save favorites to SharedPreferences
     */
    private fun saveFavorites(favorites: List<FavoriteItem>) {
        try {
            val json = adapter.toJson(favorites)
            prefs.edit().putString(KEY_FAVORITES, json).apply()
        } catch (e: Exception) {
            Timber.e(e, "Error saving favorites")
        }
    }

    companion object {
        private const val PREFS_NAME = "favorites_preferences"
        private const val KEY_FAVORITES = "favorites"
    }
}
