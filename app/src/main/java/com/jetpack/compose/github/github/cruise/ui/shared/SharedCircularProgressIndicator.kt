package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.github.github.cruise.ui.theme.Dimension
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Reusable loading indicator component
 *
 * @param modifier Modifier to be applied to the container
 *
 * Design principles:
 * - Uses theme colors for consistency
 * - Standard Material Design 3 sizing
 * - Centered in available space
 */
@Composable
fun SharedProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(Dimension.progressIndicatorSize),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = Dimension.progressIndicatorStroke
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SharedProgressIndicatorPreview() {
    GithubCruiseTheme {
        Surface {
            SharedProgressIndicator()
        }
    }
}