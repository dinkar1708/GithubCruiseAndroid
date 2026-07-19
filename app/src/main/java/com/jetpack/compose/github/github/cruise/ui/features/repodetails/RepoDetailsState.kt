package com.jetpack.compose.github.github.cruise.ui.features.repodetails

import com.jetpack.compose.github.github.cruise.domain.model.RepositoryDetails

/**
 * UI state for Repository Details screen
 */
data class RepoDetailsState(
    val repositoryDetails: RepositoryDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
