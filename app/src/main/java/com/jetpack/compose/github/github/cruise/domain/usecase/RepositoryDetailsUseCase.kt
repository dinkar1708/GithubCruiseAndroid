package com.jetpack.compose.github.github.cruise.domain.usecase

import com.jetpack.compose.github.github.cruise.data.repository.repositorydetails.RepositoryDetailsRepository
import com.jetpack.compose.github.github.cruise.domain.model.RepositoryDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for fetching repository details
 */
class RepositoryDetailsUseCase @Inject constructor(
    private val repository: RepositoryDetailsRepository
) {
    suspend operator fun invoke(owner: String, repo: String): Flow<RepositoryDetails> {
        return repository.getRepositoryDetails(owner, repo)
    }
}
