package com.jetpack.compose.github.github.cruise.ui.features.userrepository.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.domain.model.UserRepo
import com.jetpack.compose.github.github.cruise.ui.theme.AppShapes
import com.jetpack.compose.github.github.cruise.ui.theme.Elevation
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import com.jetpack.compose.github.github.cruise.ui.theme.Spacing

/**
 * Created by Dinakar Maurya on 2024/05/14.
 */
@Composable
fun UserRepoListView(
    modifier: Modifier,
    userRepoList: List<UserRepo>,
    openRepoDetails: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(userRepoList) { _, userRepo ->
            key(userRepo.id) {
                RepositoryListItem(
                    userRepo = userRepo,
                    openRepoDetails = openRepoDetails
                )
            }
        }
    }
}


@Composable
fun RepositoryListItem(userRepo: UserRepo, openRepoDetails: (String) -> Unit) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.small),
        onClick = { openRepoDetails(userRepo.htmlUrl) },
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(
            defaultElevation = Elevation.card
        ),
        shape = AppShapes.card
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium)
        ) {
            // Repository name
            Text(
                text = userRepo.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Language and Stars row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Spacing.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                // Language
                if (!userRepo.language.isNullOrBlank()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                    ) {
                        Text(
                            text = stringResource(R.string.user_repository_repo_list_language),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = userRepo.language,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Stars
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.extraSmall)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Stars",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(0.dp)
                    )
                    Text(
                        text = userRepo.stargazersCount,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Description
            if (!userRepo.description.isNullOrBlank()) {
                Text(
                    text = userRepo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = Spacing.small)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserRepositoryListPreview() {
    val repoList =
        mutableListOf(
            UserRepo(
                id = 1,
                name = "Repo",
                language = "JAVA",
                stargazersCount = "10",
                description = "Android Repo Desc Android Repo Desc Android Repo Desc",
                fork = false
            )
        )

    GithubCruiseTheme {
        UserRepoListView(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            userRepoList = repoList,
            openRepoDetails = {}
        )
    }
}