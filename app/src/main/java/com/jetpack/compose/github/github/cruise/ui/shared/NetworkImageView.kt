package com.jetpack.compose.github.github.cruise.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.jetpack.compose.github.github.cruise.ui.theme.AppShapes
import com.jetpack.compose.github.github.cruise.ui.theme.GithubCruiseTheme

/**
 * Reusable network image component with proper loading and error handling
 *
 * @param imageUrl URL of the image to load
 * @param contentDescription Accessibility description
 * @param modifier Modifier to be applied
 * @param contentScale How to scale the image within bounds
 * @param shape Shape to clip the image (defaults to circular avatar)
 *
 * Design principles:
 * - Uses Coil for efficient image loading and caching
 * - Proper content descriptions for accessibility
 * - Customizable shape for different use cases
 * - Fallback background color during loading
 */
@Composable
fun NetworkImageView(
    imageUrl: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = AppShapes.avatar
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        imageUrl?.let {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier.clip(shape),
                contentScale = contentScale
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NetworkImageViewPreview() {
    GithubCruiseTheme {
        Surface {
            NetworkImageView(imageUrl = "", contentDescription = "desc")
        }
    }
}