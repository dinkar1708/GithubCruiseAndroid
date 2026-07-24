package com.jetpack.compose.github.github.cruise.domain.model

import androidx.compose.runtime.Immutable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Domain model representing a GitHub user profile with detailed information
 *
 * @Immutable for Compose performance - see package-info.md for details
 * Created by Dinakar Maurya on 2024/05/14.
 */
@Immutable
@JsonClass(generateAdapter = true)
data class UserProfile(
    val id: Long = 0,
    @Json(name = "avatar_url")
    val avatarUrl: String = "",
    val login: String,
    // nullable as per api doc
    /*
     "name": {
          "type": [
            "string",
            "null"
          ],
     */
    val name: String? = "",
    val followers: Int = 0,
    val following: Int = 0,
    @Json(name = "public_repos")
    val publicRepos: Int = 0,
)
