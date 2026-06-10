package com.jetpack.compose.github.github.cruise.ui.features.webview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jetpack.compose.github.github.cruise.ui.shared.AppActionBarView
import com.jetpack.compose.github.github.cruise.ui.shared.SharedWebView

/**
 * Common WebView screen for displaying web content
 *
 * @param navController Navigation controller
 * @param url URL to display
 * @param title Title for the app bar
 */
@Composable
fun CommonWebViewScreen(
    navController: NavHostController,
    url: String,
    title: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        AppActionBarView(
            modifier = Modifier.fillMaxWidth(),
            headerText = title,
            showBackButton = true,
            onBackClick = {
                navController.popBackStack()
            }
        )

        SharedWebView(url = url)
    }
}
