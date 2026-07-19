package com.jetpack.compose.github.github.cruise.ui.features.repositorysearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.compose.github.github.cruise.data.network.model.ApiError
import com.jetpack.compose.github.github.cruise.di.DefaultDispatcher
import com.jetpack.compose.github.github.cruise.domain.usecase.RepositorySearchUseCase
import com.jetpack.compose.github.github.cruise.ui.features.repositorysearch.state.RepositorySearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModel for Repository Search Screen
 * Feature 2.1: Repository Search Screen
 */
@HiltViewModel
class RepositorySearchViewModel @Inject constructor(
    private val repositorySearchUseCase: RepositorySearchUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(RepositorySearchState())
    val uiState: StateFlow<RepositorySearchState> = _uiState.asStateFlow()

    private var searchQuery: String = ""
    private var page = 1
    private val pageSize = 40
    private var totalResults = 0
    private var isLoadingApiData = false

    init {
        _uiState.update {
            RepositorySearchState(
                errorMessage = "Enter repository name to search",
                isLoading = false
            )
        }
    }

    fun searchRepositories(query: String) {
        Timber.d("searchRepositories() called with query: $query")
        searchQuery = query
        page = 1
        totalResults = 0
        isLoadingApiData = true
        _uiState.update { RepositorySearchState(searchQuery = query) }
        loadRepositories()
    }

    fun loadNextPage() {
        if (!isLoadingApiData && shouldLoadNextPage()) {
            isLoadingApiData = true
            page++
            Timber.d("loadNextPage() loading page $page")
            loadRepositories()
        }
    }

    fun updateLastVisibleIndex(index: Int) {
        _uiState.update { it.copy(lastVisibleItemIndex = index) }
    }

    private fun loadRepositories() {
        viewModelScope.launch(dispatcher) {
            _uiState.update { it.copy(isLoading = true, errorMessage = "") }

            repositorySearchUseCase.searchRepositories(searchQuery, page, pageSize)
                .catch { e ->
                    Timber.e(e, "Error searching repositories")
                    isLoadingApiData = false
                    val errorMsg = when (e) {
                        is ApiError -> e.message
                        else -> e.message ?: "Unknown error occurred"
                    }
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = errorMsg)
                    }
                }
                .collect { searchResult ->
                    isLoadingApiData = false
                    totalResults = searchResult.totalCount

                    val currentList = _uiState.value.repositories
                    val newList = if (page == 1) {
                        searchResult.repositories
                    } else {
                        currentList + searchResult.repositories
                    }

                    _uiState.update {
                        it.copy(
                            repositories = newList,
                            isLoading = false,
                            hasMorePages = newList.size < totalResults
                        )
                    }
                }
        }
    }

    private fun shouldLoadNextPage(): Boolean {
        val currentSize = _uiState.value.repositories.size
        return currentSize < totalResults && _uiState.value.hasMorePages
    }

    companion object {
        private const val TAG = "RepositorySearchVM"
    }
}
