package com.jetpack.compose.github.github.cruise.domain.model

import androidx.compose.runtime.Immutable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Domain model representing a GitHub repository owned by a user
 *
 * @Immutable for Compose performance - see package-info.md for details
 * Created by Dinakar Maurya on 2024/05/14.
 */
@Immutable
@JsonClass(generateAdapter = true)
data class UserRepo(
    val id: Long = 0,
    val name: String = "",
    // can be null as per api doc
    val language: String? = "",
    @Json(name = "stargazers_count")
    val stargazersCount: String = "",
    // can be null as per api doc
    val description: String? = "",
    @Json(name = "full_name")
    val fullName: String = "",
    @Json(name = "html_url")
    val htmlUrl: String = "",
    val fork: Boolean,
)