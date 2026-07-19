package com.jetpack.compose.github.github.cruise.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Domain model for search repositories result
 * Used for Feature 2.1: Repository Search Screen
 * API: GET /search/repositories
 */
@JsonClass(generateAdapter = true)
data class SearchRepository(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    @Json(name = "items")
    val repositories: List<Repository>
)

/**
 * Domain model for a repository
 */
@JsonClass(generateAdapter = true)
data class Repository(
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    @Json(name = "owner")
    val owner: RepositoryOwner,
    @Json(name = "description")
    val description: String?,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "language")
    val language: String?,
    @Json(name = "stargazers_count")
    val stargazersCount: Int,
    @Json(name = "forks_count")
    val forksCount: Int,
    @Json(name = "watchers_count")
    val watchersCount: Int,
    @Json(name = "open_issues_count")
    val openIssuesCount: Int,
    @Json(name = "score")
    val score: Double,
    @Json(name = "fork")
    val fork: Boolean,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String
)

/**
 * Domain model for repository owner
 */
@JsonClass(generateAdapter = true)
data class RepositoryOwner(
    @Json(name = "login")
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "html_url")
    val htmlUrl: String
)
