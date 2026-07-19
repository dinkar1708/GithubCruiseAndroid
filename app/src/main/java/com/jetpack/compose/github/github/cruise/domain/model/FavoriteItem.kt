package com.jetpack.compose.github.github.cruise.domain.model

import com.squareup.moshi.JsonClass

/**
 * Represents a favorited item (user or repository)
 */
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
