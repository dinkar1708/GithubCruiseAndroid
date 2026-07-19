package com.jetpack.compose.github.github.cruise.domain.usecase

import com.jetpack.compose.github.github.cruise.data.repository.repositorysearch.RepositorySearchRepository
import com.jetpack.compose.github.github.cruise.domain.model.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for searching GitHub repositories
 * Feature 2.1: Repository Search Screen
 */
class RepositorySearchUseCase @Inject constructor(
    private val repositorySearchRepository: RepositorySearchRepository
) {
    suspend fun searchRepositories(
        query: String,
        page: Int,
        pageSize: Int,
    ): Flow<SearchRepository> =
        repositorySearchRepository.searchRepositories(
            query = query,
            page = page,
            pageSize = pageSize
        )
}
