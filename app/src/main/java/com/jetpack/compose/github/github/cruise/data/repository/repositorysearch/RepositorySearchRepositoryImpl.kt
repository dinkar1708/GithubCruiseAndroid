package com.jetpack.compose.github.github.cruise.data.repository.repositorysearch

import com.jetpack.compose.github.github.cruise.data.network.NetworkDataSource
import com.jetpack.compose.github.github.cruise.di.DefaultDispatcher
import com.jetpack.compose.github.github.cruise.domain.model.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of RepositorySearchRepository
 * Feature 2.1: Repository Search Screen
 */
class RepositorySearchRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : RepositorySearchRepository {

    override suspend fun searchRepositories(
        query: String,
        page: Int,
        pageSize: Int,
    ): Flow<SearchRepository> = flow {
        val repositories = withContext(dispatcher) {
            networkDataSource.searchRepositories(
                query = query,
                page = page,
                pageSize = pageSize
            )
        }
        emit(repositories)
    }.catch { e ->
        Timber.e(e, "Error searching repositories: $e")
        throw e
    }.flowOn(dispatcher)
}
