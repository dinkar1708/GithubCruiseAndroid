package com.jetpack.compose.github.github.cruise.ui.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Bottom navigation item data class
 */
data class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null
)

/**
 * Home screen with bottom navigation
 *
 * @param usersListContent Content for the Users tab
 * @param profileContent Content for the Profile tab
 * @param settingsContent Content for the Settings tab
 */
@Composable
fun HomeScreen(
    usersListContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
    settingsContent: @Composable () -> Unit
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    val tabs = listOf(
        BottomNavItem(
            title = stringResource(R.string.bottom_nav_home),
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavItem(
            title = stringResource(R.string.bottom_nav_profile),
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        ),
        BottomNavItem(
            title = stringResource(R.string.bottom_nav_settings),
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                tonalElevation = com.jetpack.compose.github.github.cruise.ui.theme.Elevation.level2
            ) {
                tabs.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (selectedTabIndex == index) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    },
                                    contentDescription = item.title
                                )
                            }
                        },
                        label = {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTabIndex) {
                0 -> usersListContent()
                1 -> profileContent()
                2 -> settingsContent()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    GithubCruiseTheme {
        HomeScreen(
            usersListContent = { Text("Users List") },
            profileContent = { Text("Profile") },
            settingsContent = { Text("Settings") }
        )
    }
}
