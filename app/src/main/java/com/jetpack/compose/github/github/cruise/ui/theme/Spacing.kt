package com.jetpack.compose.github.github.cruise.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Material Design 3 Spacing System
 * Following the 4dp grid system
 * Reference: https://m3.material.io/foundations/layout/applying-layout/spacing
 */
object Spacing {
    // Extra Small spacing - Use for very tight spacing
    val extraSmall: Dp = 4.dp

    // Small spacing - Use for compact layouts
    val small: Dp = 8.dp

    // Medium spacing - Use for most UI elements (default)
    val medium: Dp = 16.dp

    // Large spacing - Use for section separation
    val large: Dp = 24.dp

    // Extra Large spacing - Use for major section breaks
    val extraLarge: Dp = 32.dp

    // Specific token aliases for better semantic meaning
    val paddingTiny: Dp = 2.dp
    val paddingSmall: Dp = small
    val paddingMedium: Dp = medium
    val paddingLarge: Dp = large

    val marginSmall: Dp = small
    val marginMedium: Dp = medium
    val marginLarge: Dp = large

    // Component-specific spacing
    val listItemSpacing: Dp = 2.dp
    val cardPadding: Dp = medium
    val screenPadding: Dp = medium
    val iconSpacing: Dp = small
}
