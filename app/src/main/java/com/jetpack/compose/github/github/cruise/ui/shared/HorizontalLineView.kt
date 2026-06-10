package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jetpack.compose.github.github.cruise.ui.theme.Dimension

/**
 * Reusable horizontal divider component
 *
 * @param modifier Modifier to be applied
 *
 * Design principles:
 * - Uses theme colors for consistency
 * - Standard Material Design 3 divider thickness
 */
@Composable
fun HorizontalLineView(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.outline,
        thickness = Dimension.dividerThickness
    )
}