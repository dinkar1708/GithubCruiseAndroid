package com.jetpack.compose.github.github.cruise.ui.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Favorite button that toggles between filled and outlined star
 */
@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onToggleFavorite,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = if (isFavorite) {
                stringResource(R.string.favorites_remove)
            } else {
                stringResource(R.string.favorites_add)
            },
            tint = if (isFavorite) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.outline
            }
        )
    }
}

@Preview
@Composable
private fun FavoriteButtonPreview() {
    GithubCruiseTheme {
        FavoriteButton(
            isFavorite = false,
            onToggleFavorite = {}
        )
    }
}

@Preview
@Composable
private fun FavoriteButtonFavoritedPreview() {
    GithubCruiseTheme {
        FavoriteButton(
            isFavorite = true,
            onToggleFavorite = {}
        )
    }
}
