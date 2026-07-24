package com.jetpack.compose.github.github.cruise.domain.model

import androidx.compose.runtime.Immutable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Domain model representing a GitHub user
 *
 * @Immutable for Compose performance - see package-info.md for details
 * Created by Dinakar Maurya on 2024/05/12
 */
@Immutable
@JsonClass(generateAdapter = true)
data class User(
    val id: Long,
    val login: String = "",
    val score: Double = 0.0,
    @Json(name = "avatar_url")
    val avatarUrl: String = "",
)
