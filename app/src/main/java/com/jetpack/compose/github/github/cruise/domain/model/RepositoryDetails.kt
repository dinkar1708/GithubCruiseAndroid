package com.jetpack.compose.github.github.cruise.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Detailed repository information from API-5
 */
@JsonClass(generateAdapter = true)
data class RepositoryDetails(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "owner") val owner: RepositoryOwner,
    @Json(name = "description") val description: String?,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "clone_url") val cloneUrl: String,
    @Json(name = "ssh_url") val sshUrl: String,
    @Json(name = "homepage") val homepage: String?,
    @Json(name = "language") val language: String?,
    @Json(name = "stargazers_count") val stargazersCount: Int,
    @Json(name = "watchers_count") val watchersCount: Int,
    @Json(name = "forks_count") val forksCount: Int,
    @Json(name = "open_issues_count") val openIssuesCount: Int,
    @Json(name = "size") val size: Int,
    @Json(name = "default_branch") val defaultBranch: String,
    @Json(name = "topics") val topics: List<String>?,
    @Json(name = "license") val license: License?,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "pushed_at") val pushedAt: String,
    @Json(name = "fork") val fork: Boolean,
    @Json(name = "archived") val archived: Boolean,
    @Json(name = "disabled") val disabled: Boolean,
    @Json(name = "visibility") val visibility: String?,
    @Json(name = "subscribers_count") val subscribersCount: Int?
)

@JsonClass(generateAdapter = true)
data class License(
    @Json(name = "key") val key: String,
    @Json(name = "name") val name: String,
    @Json(name = "spdx_id") val spdxId: String?,
    @Json(name = "url") val url: String?,
    @Json(name = "node_id") val nodeId: String?
)
