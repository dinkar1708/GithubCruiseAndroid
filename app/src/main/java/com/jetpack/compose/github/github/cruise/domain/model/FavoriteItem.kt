package com.jetpack.compose.github.github.cruise.domain.model

import androidx.compose.runtime.Immutable
import com.squareup.moshi.JsonClass

/**
 * Represents a favorited item (user or repository)
 *
 * @Immutable for Compose performance - see package-info.md for details
 */
@Immutable
@JsonClass(generateAdapter = true)
data class FavoriteItem(
    val id: String,
    val type: FavoriteType,
    val name: String,
    val description: String?,
    val avatarUrl: String?,
    val url: String,
    val timestamp: Long = System.currentTimeMillis()
)

enum class FavoriteType {
    USER,
    REPOSITORY
}
