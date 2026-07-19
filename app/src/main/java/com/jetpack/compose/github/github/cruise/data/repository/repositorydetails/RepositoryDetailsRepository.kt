package com.jetpack.compose.github.github.cruise.data.repository.repositorydetails

import com.jetpack.compose.github.github.cruise.domain.model.RepositoryDetails
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for fetching repository details
 */
interface RepositoryDetailsRepository {

    /**
     * Get detailed repository information
     * @param owner Repository owner/organization name
     * @param repo Repository name
     * @return Flow of RepositoryDetails
     */
    suspend fun getRepositoryDetails(owner: String, repo: String): Flow<RepositoryDetails>
}
