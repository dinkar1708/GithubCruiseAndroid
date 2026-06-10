package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.ui.theme.Dimension
import com.jetpack.compose.github.github.cruise.ui.theme.Elevation
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import com.jetpack.compose.github.github.cruise.ui.theme.Spacing

/**
 * Reusable AppBar component following Material Design 3 guidelines
 *
 * @param modifier Modifier to be applied to the AppBar
 * @param headerText Title text to display
 * @param showBackButton Whether to show the back navigation button
 * @param onBackClick Callback when back button is clicked
 *
 * Design principles:
 * - Uses Material Theme colors for adaptive theming
 * - Consistent spacing using design tokens
 * - Proper elevation for depth perception
 * - Accessible with proper content descriptions
 */
@Composable
fun AppActionBarView(
    modifier: Modifier = Modifier,
    headerText: String,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = Elevation.level0,
        tonalElevation = Elevation.level1
    ) {
        Column(
            modifier = Modifier.padding(
                vertical = Spacing.small,
                horizontal = Spacing.medium
            ),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(0.dp)
            ) {
                if (showBackButton) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(Dimension.appBarIconSize)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(Dimension.appBarIconSize),
                        )
                    }
                }

                Text(
                    text = headerText,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = if (showBackButton) {
                        Modifier
                            .padding(horizontal = Spacing.extraLarge)
                            .fillMaxWidth()
                    } else {
                        Modifier.fillMaxWidth()
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCommonHeaderPreview() {
    GithubCruiseTheme {
        Surface {
            AppActionBarView(modifier = Modifier.fillMaxWidth(),
                headerText = "Text",
                showBackButton = false,
                onBackClick = {})
        }
    }
}
