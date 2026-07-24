package com.jetpack.compose.github.github.cruise.domain.model

import androidx.compose.runtime.Immutable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Domain model representing GitHub user search results
 *
 * @Immutable for Compose performance - see package-info.md for details
 * Created by Dinakar Maurya on 2024/05/12
 */
@Immutable
@JsonClass(generateAdapter = true)
data class SearchUser(
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    // use readable name
    @Json(name = "items")
    val users: List<User>
)