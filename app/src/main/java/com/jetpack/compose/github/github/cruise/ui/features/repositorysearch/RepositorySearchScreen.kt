package com.jetpack.compose.github.github.cruise.ui.features.repositorysearch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jetpack.compose.github.github.cruise.R
import com.jetpack.compose.github.github.cruise.domain.model.Repository
import com.jetpack.compose.github.github.cruise.ui.MainDestinations.USER_REPO_DETAILS_SCREEN_ROUTE
import com.jetpack.compose.github.github.cruise.ui.features.repositorysearch.view.RepositoriesListView
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.StateContentBox
import com.jetpack.compose.github.github.cruise.ui.shared.utils.CommonUtils
import com.jetpack.compose.github.github.cruise.ui.theme.AppShapes
import com.jetpack.compose.github.github.cruise.ui.theme.Elevation
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import com.jetpack.compose.github.github.cruise.ui.theme.Spacing
import kotlinx.coroutines.delay

/**
 * Repository Search Screen
 * Feature 2.1: Repository Search Screen
 */
@Composable
fun RepositorySearchScreen(
    navController: NavHostController,
    viewModel: RepositorySearchViewModel
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    RepositorySearchScreenContent(
        isLoading = viewState.isLoading,
        repositories = viewState.repositories,
        lastVisibleItemIndex = viewState.lastVisibleItemIndex,
        errorMessage = viewState.errorMessage,
        onItemClick = { repository ->
            val encodedUrl = CommonUtils.encodeUrl(repository.htmlUrl)
            navController.navigate("$USER_REPO_DETAILS_SCREEN_ROUTE/$encodedUrl")
        },
        onSearchSubmitted = { viewModel.searchRepositories(it) },
        onClearInput = {
            viewModel.searchRepositories("")
        },
        onListScrolledToEnd = { index ->
            viewModel.updateLastVisibleIndex(index)
            viewModel.loadNextPage()
        }
    )
}

@Composable
fun RepositorySearchScreenContent(
    isLoading: Boolean,
    repositories: List<Repository>,
    lastVisibleItemIndex: Int,
    errorMessage: String,
    onItemClick: (Repository) -> Unit,
    onSearchSubmitted: (String) -> Unit,
    onClearInput: () -> Unit,
    onListScrolledToEnd: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        AppActionBarView(
            headerText = stringResource(R.string.repository_search_title),
            showBackButton = false
        )

        SearchTextField(
            onSearchSubmitted = onSearchSubmitted,
            onClearInput = onClearInput
        )

        StateContentBox(
            modifier = Modifier.fillMaxSize(),
            isLoading = isLoading,
            errorMessage = errorMessage
        ) {
            RepositoriesListView(
                repositories = repositories,
                lastVisibleItemIndex = lastVisibleItemIndex,
                onItemClick = onItemClick,
                onListScrolledToEnd = onListScrolledToEnd
            )
        }
    }
}

@Composable
private fun SearchTextField(
    onSearchSubmitted: (String) -> Unit,
    onClearInput: () -> Unit
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(searchText) {
        if (searchText.length >= 2) {
            delay(500)
            onSearchSubmitted(searchText)
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.medium),
        shape = AppShapes.searchBar,
        shadowElevation = Elevation.level2,
        color = MaterialTheme.colorScheme.surface
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .semantics { testTag = "repository_search_input" },
            placeholder = {
                Text(stringResource(R.string.repository_search_hint))
            },
            trailingIcon = {
                if (searchText.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        modifier = Modifier.clickable {
                            searchText = ""
                            onClearInput()
                        }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    if (searchText.isNotEmpty()) {
                        onSearchSubmitted(searchText)
                    }
                }
            ),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepositorySearchScreenPreview() {
    GithubCruiseTheme {
        RepositorySearchScreenContent(
            isLoading = false,
            repositories = emptyList(),
            lastVisibleItemIndex = 0,
            errorMessage = "",
            onItemClick = {},
            onSearchSubmitted = {},
            onClearInput = {},
            onListScrolledToEnd = {}
        )
    }
}
