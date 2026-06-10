package com.jetpack.compose.github.github.cruise.ui.features.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.theme.AppShapes
import com.jetpack.compose.github.github.cruise.ui.theme.Dimension
import com.jetpack.compose.github.github.cruise.ui.theme.Elevation
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import com.jetpack.compose.github.github.cruise.ui.theme.Spacing

/**
 * Profile screen displaying user information
 */
@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.medium),
            headerText = stringResource(R.string.profile_title),
            showBackButton = false
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(Spacing.large))

            // Profile Avatar
            Surface(
                modifier = Modifier.size(Dimension.avatarSizeExtraLarge),
                shape = AppShapes.avatar,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(R.string.profile_avatar),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Spacing.medium)
                )
            }

            Spacer(modifier = Modifier.height(Spacing.large))

            // User Name
            Text(
                text = stringResource(R.string.profile_user_name),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(Spacing.extraLarge))

            // Profile Info Card
            ProfileInfoCard(
                title = stringResource(R.string.profile_email_label),
                value = stringResource(R.string.profile_email_value),
                icon = Icons.Filled.Email
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            // GitHub Username Card
            ProfileInfoCard(
                title = stringResource(R.string.profile_github_label),
                value = stringResource(R.string.profile_github_value),
                icon = Icons.Filled.Person
            )
        }
    }
}

@Composable
fun ProfileInfoCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Elevation.card
        ),
        shape = AppShapes.card
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(Dimension.iconSizeLarge)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = Spacing.medium)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    GithubCruiseTheme {
        ProfileScreen()
    }
}
