package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.compose.github.github.cruise.ui.theme.Dimension
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme
import com.jetpack.compose.github.github.cruise.ui.theme.Spacing

/**
 * Reusable error state component
 *
 * @param errorMessage Error message to display
 * @param modifier Modifier to be applied
 *
 * Design principles:
 * - Clear visual hierarchy with icon and message
 * - Uses error color from theme
 * - Consistent spacing
 * - Accessible content descriptions
 */
@Composable
fun SharedErrorView(
    errorMessage: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = Spacing.extraLarge)
    ) {
        Icon(
            imageVector = Icons.Filled.Info,
            contentDescription = "Error",
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(Dimension.iconSizeExtraLarge)
        )
        Spacer(modifier = Modifier.height(Spacing.medium))
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SharedErrorViewPreview() {
    GithubCruiseTheme {
        Surface {
            SharedErrorView(errorMessage = "Error")
        }
    }
}
