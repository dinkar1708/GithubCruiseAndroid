package com.jetpack.compose.github.github.cruise.data.repository.repositorydetails

import com.jetpack.compose.github.github.cruise.data.network.NetworkDataSource
import com.jetpack.compose.github.github.cruise.di.DefaultDispatcher
import com.jetpack.compose.github.github.cruise.domain.model.RepositoryDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of RepositoryDetailsRepository
 */
class RepositoryDetailsRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : RepositoryDetailsRepository {

    override suspend fun getRepositoryDetails(
        owner: String,
        repo: String
    ): Flow<RepositoryDetails> = flow {
        val details = withContext(dispatcher) {
            networkDataSource.getRepositoryDetails(owner, repo)
        }
        emit(details)
    }.catch { e ->
        Timber.e(e, "Error fetching repository details for $owner/$repo")
        throw e
    }.flowOn(dispatcher)
}
