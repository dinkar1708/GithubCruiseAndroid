package com.jetpack.compose.github.github.cruise.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/**
 * Material Design 3 Shape System
 * Defines corner radius tokens for components
 * Reference: https://m3.material.io/styles/shape/shape-scale-tokens
 */
object AppShapes {
    // None - No rounding (0dp)
    val none = RoundedCornerShape(0.dp)

    // Extra Small - Minimal rounding (4dp)
    val extraSmall = RoundedCornerShape(4.dp)

    // Small - Small components like chips, small buttons (8dp)
    val small = RoundedCornerShape(8.dp)

    // Medium - Default for cards, dialogs (12dp)
    val medium = RoundedCornerShape(12.dp)

    // Large - Prominent components like large cards (16dp)
    val large = RoundedCornerShape(16.dp)

    // Extra Large - Maximum rounding for special components (28dp)
    val extraLarge = RoundedCornerShape(28.dp)

    // Full - Fully rounded (pills, search bars)
    val full = RoundedCornerShape(50)

    // Semantic aliases
    val button = medium
    val card = medium
    val dialog = extraLarge
    val textField = extraSmall
    val searchBar = full
    val avatar = full
}
