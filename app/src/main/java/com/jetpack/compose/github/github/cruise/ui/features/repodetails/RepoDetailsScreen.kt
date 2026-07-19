package com.jetpack.compose.github.github.cruise.ui.features.repodetails

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.domain.model.RepositoryDetails
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.NetworkImageView
import com.jetpack.compose.github.github.cruise.ui.theme.Dimension
import com.jetpack.compose.github.github.cruise.ui.theme.Elevation
import com.jetpack.compose.github.github.cruise.ui.theme.Spacing
import com.jetpack.compose.github.github.cruise.ui.widgets.FavoriteButton
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteItem
import com.jetpack.compose.github.github.cruise.domain.model.FavoriteType
import com.jetpack.compose.github.github.cruise.ui.features.favorites.FavoritesViewModel

/**
 * Enhanced Repository Details screen showing rich information
 */
@Composable
fun RepoDetailsScreen(
    navController: NavHostController,
    htmlUrl: String,
    viewModel: RepoDetailsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    val favoritesState by favoritesViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(htmlUrl) {
        viewModel.loadRepositoryDetails(htmlUrl)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppActionBarView(
            modifier = Modifier.fillMaxWidth(),
            headerText = stringResource(R.string.repository_details_title),
            showBackButton = true,
            onBackClick = { navController.popBackStack() },
            actions = {
                // Favorite button for repository
                viewState.repositoryDetails?.let { repo ->
                    val repoId = "${repo.owner.login}/${repo.name}"
                    val isFavorite = favoritesState.favorites.any {
                        it.id == repoId && it.type == FavoriteType.REPOSITORY
                    }
                    FavoriteButton(
                        isFavorite = isFavorite,
                        onToggleFavorite = {
                            if (isFavorite) {
                                favoritesViewModel.removeFavorite(repoId, FavoriteType.REPOSITORY)
                            } else {
                                val favoriteItem = FavoriteItem(
                                    id = repoId,
                                    type = FavoriteType.REPOSITORY,
                                    name = repo.fullName,
                                    description = repo.description,
                                    avatarUrl = repo.owner.avatarUrl,
                                    url = repo.htmlUrl
                                )
                                favoritesViewModel.addFavorite(favoriteItem)
                            }
                        }
                    )
                }
            }
        )

        when {
            viewState.isLoading -> LoadingView()
            viewState.error != null -> ErrorView(viewState.error!!)
            viewState.repositoryDetails != null -> {
                RepositoryDetailsContent(
                    repository = viewState.repositoryDetails!!,
                    onOpenInBrowser = {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
                    },
                    onCopyCloneUrl = {
                        copyToClipboard(context, it)
                    },
                    onShare = {
                        shareRepository(context, it)
                    }
                )
            }
        }
    }
}

@Composable
private fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorView(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun RepositoryDetailsContent(
    repository: RepositoryDetails,
    onOpenInBrowser: (String) -> Unit,
    onCopyCloneUrl: (String) -> Unit,
    onShare: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.medium)
    ) {
        // Header with owner avatar and repo name
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImageView(
                imageUrl = repository.owner.avatarUrl,
                contentDescription = repository.owner.login,
                modifier = Modifier.size(Dimension.avatarSizeMedium)
            )
            Spacer(modifier = Modifier.width(Spacing.medium))
            Column {
                Text(
                    text = repository.fullName,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                if (!repository.description.isNullOrEmpty()) {
                    Text(
                        text = repository.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Spacing.medium))

        // Stats Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.medium),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("Stars", repository.stargazersCount)
                StatItem("Forks", repository.forksCount)
                StatItem("Issues", repository.openIssuesCount)
                StatItem("Watchers", repository.watchersCount)
            }
        }

        Spacer(modifier = Modifier.height(Spacing.medium))

        // Topics
        if (!repository.topics.isNullOrEmpty()) {
            Text(
                text = "Topics",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(Spacing.small))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(Spacing.small),
                verticalArrangement = Arrangement.spacedBy(Spacing.small)
            ) {
                repository.topics.forEach { topic ->
                    AssistChip(
                        onClick = { },
                        label = { Text(topic) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(Spacing.medium))
        }

        // Info Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = Elevation.level2)
        ) {
            Column(modifier = Modifier.padding(Spacing.medium)) {
                if (repository.language != null) {
                    InfoRow("Language", repository.language)
                }
                if (repository.license != null) {
                    InfoRow("License", repository.license.name)
                }
                InfoRow("Default Branch", repository.defaultBranch)
                if (!repository.homepage.isNullOrEmpty()) {
                    InfoRow("Homepage", repository.homepage)
                }
            }
        }

        Spacer(modifier = Modifier.height(Spacing.medium))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            Button(
                onClick = { onOpenInBrowser(repository.htmlUrl) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Open in Browser")
            }
            OutlinedButton(
                onClick = { onCopyCloneUrl(repository.cloneUrl) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Copy Clone URL")
            }
        }

        Spacer(modifier = Modifier.height(Spacing.small))

        OutlinedButton(
            onClick = { onShare(repository.htmlUrl) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Share, contentDescription = null)
            Spacer(modifier = Modifier.width(Spacing.small))
            Text("Share Repository")
        }
    }
}

@Composable
private fun StatItem(label: String, count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.extraSmall)
    ) {
        Text(
            text = "$label:",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(120.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Clone URL", text)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
}

private fun shareRepository(context: Context, url: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, url)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Repository"))
}
