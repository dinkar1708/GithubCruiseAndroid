package com.jetpack.compose.github.github.cruise.ui.features.favorites.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteItem
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteType
import com.jetpack.compose.github.github.cruise.ui.shared.NetworkImageView
import com.jetpack.compose.github.github.cruise.ui.theme.Dimension
import com.jetpack.compose.github.github.cruise.ui.theme.Elevation
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import com.jetpack.compose.github.github.cruise.ui.theme.Spacing

/**
 * List view for favorites
 */
@Composable
fun FavoritesListView(
    favorites: List<FavoriteItem>,
    onFavoriteClick: (FavoriteItem) -> Unit,
    onRemoveFavorite: (String, FavoriteType) -> Unit,
    onClearAll: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Clear all button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(onClick = onClearAll) {
                Text(stringResource(R.string.favorites_clear_all))
            }
        }

        // Favorites list
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            items(
                items = favorites,
                key = { "${it.type.name}_${it.id}" }
            ) { favorite ->
                FavoriteItemCard(
                    favorite = favorite,
                    onClick = { onFavoriteClick(favorite) },
                    onRemove = { onRemoveFavorite(favorite.id, favorite.type) }
                )
            }
        }
    }
}

/**
 * Card for a single favorite item
 */
@Composable
private fun FavoriteItemCard(
    favorite: FavoriteItem,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.medium)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                // Avatar or icon
                if (!favorite.avatarUrl.isNullOrEmpty()) {
                    NetworkImageView(
                        imageUrl = favorite.avatarUrl,
                        contentDescription = favorite.name,
                        modifier = Modifier.size(Dimension.avatarSizeSmall)
                    )
                } else {
                    Icon(
                        imageVector = when (favorite.type) {
                            FavoriteType.USER -> Icons.Default.Person
                            FavoriteType.REPOSITORY -> Icons.Default.Star
                        },
                        contentDescription = null,
                        modifier = Modifier.size(Dimension.avatarSizeSmall)
                    )
                }

                // Content
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = favorite.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (!favorite.description.isNullOrEmpty()) {
                        Text(
                            text = favorite.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.outline,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    // Type badge
                    Text(
                        text = when (favorite.type) {
                            FavoriteType.USER -> stringResource(R.string.favorites_type_user)
                            FavoriteType.REPOSITORY -> stringResource(R.string.favorites_type_repository)
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Remove button
            IconButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.favorites_remove),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FavoriteItemCardPreview() {
    GithubCruiseTheme {
        FavoriteItemCard(
            favorite = FavoriteItem(
                id = "1",
                type = FavoriteType.USER,
                name = "dinkar1708",
                description = "Android Developer",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                url = "https://github.com/dinkar1708"
            ),
            onClick = {},
            onRemove = {}
        )
    }
}
