package com.jetpack.compose.github.github.cruise.ui.features.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.compose.github.github.cruise.data.repository.favorites.FavoritesRepository
import com.jetpack.compose.github.github.cruise.di.DefaultDispatcher
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteItem
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Favorites screen
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesState())
    val uiState: StateFlow<FavoritesState> = _uiState.asStateFlow()

    init {
        observeFavorites()
    }

    /**
     * Observe favorites from repository
     */
    private fun observeFavorites() {
        favoritesRepository.favorites
            .onEach { favorites ->
                _uiState.update { it.copy(favorites = favorites, isLoading = false) }
            }
            .catch { e ->
                Timber.e(e, "Error observing favorites")
                _uiState.update { it.copy(error = e.message, isLoading = false) }
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }

    /**
     * Add item to favorites
     */
    fun addFavorite(item: FavoriteItem) {
        viewModelScope.launch(dispatcher) {
            try {
                favoritesRepository.addFavorite(item)
            } catch (e: Exception) {
                Timber.e(e, "Error adding favorite")
                _uiState.update { it.copy(error = "Failed to add favorite") }
            }
        }
    }

    /**
     * Remove item from favorites
     */
    fun removeFavorite(itemId: String, type: FavoriteType) {
        viewModelScope.launch(dispatcher) {
            try {
                favoritesRepository.removeFavorite(itemId, type)
            } catch (e: Exception) {
                Timber.e(e, "Error removing favorite")
                _uiState.update { it.copy(error = "Failed to remove favorite") }
            }
        }
    }

    /**
     * Clear all favorites
     */
    fun clearAllFavorites() {
        viewModelScope.launch(dispatcher) {
            try {
                favoritesRepository.clearFavorites()
            } catch (e: Exception) {
                Timber.e(e, "Error clearing favorites")
                _uiState.update { it.copy(error = "Failed to clear favorites") }
            }
        }
    }

    /**
     * Clear error message
     */
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
