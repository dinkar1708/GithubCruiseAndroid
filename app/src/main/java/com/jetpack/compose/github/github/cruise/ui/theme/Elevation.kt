package com.jetpack.compose.github.github.cruise.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Material Design 3 Elevation System
 * Defines standard elevation levels for components
 * Reference: https://m3.material.io/styles/elevation/tokens
 */
object Elevation {
    // Level 0 - No elevation (flat on surface)
    val level0: Dp = 0.dp

    // Level 1 - Slight elevation (cards at rest, search bars)
    val level1: Dp = 1.dp

    // Level 2 - Medium elevation (buttons, FABs at rest)
    val level2: Dp = 3.dp

    // Level 3 - Elevated (modal dialogs, menus)
    val level3: Dp = 6.dp

    // Level 4 - High elevation (navigation drawer)
    val level4: Dp = 8.dp

    // Level 5 - Maximum elevation (bottom sheets)
    val level5: Dp = 12.dp

    // Semantic aliases
    val card: Dp = level1
    val appBar: Dp = level2
    val dialog: Dp = level3
    val navigationDrawer: Dp = level4
    val modalBottomSheet: Dp = level5
}
