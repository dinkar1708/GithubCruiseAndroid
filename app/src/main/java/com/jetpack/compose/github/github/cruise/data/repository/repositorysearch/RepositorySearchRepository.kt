package com.jetpack.compose.github.github.cruise.data.repository.repositorysearch

import com.jetpack.compose.github.github.cruise.domain.model.SearchRepository
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for searching GitHub repositories
 * Feature 2.1: Repository Search Screen
 * API: GET /search/repositories
 */
interface RepositorySearchRepository {
    suspend fun searchRepositories(
        query: String,
        page: Int,
        pageSize: Int,
    ): Flow<SearchRepository>
}
