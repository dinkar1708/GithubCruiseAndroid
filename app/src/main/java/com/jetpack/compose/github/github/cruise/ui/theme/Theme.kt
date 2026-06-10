package com.jetpack.compose.github.github.cruise.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Material Design 3 Theme Configuration
 * Supports both Light and Dark modes
 */

private val LightColorScheme = lightColorScheme(
    // Primary colors - Brand purple/pink
    primary = DarkColor,
    onPrimary = White,
    primaryContainer = WhiteLight2,
    onPrimaryContainer = DarkColor,

    // Surface colors - Clean white surfaces
    surface = White,
    onSurface = BLACK_DARK,
    surfaceVariant = CardDialog,
    onSurfaceVariant = BLACK_DARK,
    surfaceTint = DarkColor,

    // Background colors - All white
    background = White,
    onBackground = BLACK_DARK,

    // Error colors
    error = Alert,
    onError = White,

    // Additional colors
    outline = TextLight,
    outlineVariant = CardDialog,
    scrim = BLACK_DARK.copy(alpha = 0.32f)
)

private val DarkColorScheme = darkColorScheme(
    // Primary colors - Keep brand colors visible in dark mode
    primary = MediumDarkColor,
    onPrimary = White,
    primaryContainer = DarkColor,
    onPrimaryContainer = WhiteLight,

    // Surface colors - Proper dark surfaces
    surface = DarkSurface,
    onSurface = WhiteLight,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextLight,
    surfaceTint = MediumDarkColor,

    // Background colors - Dark background
    background = DarkBackground,
    onBackground = WhiteLight,

    // Error colors
    error = Alert,
    onError = BLACK_DARK,

    // Additional colors
    outline = SmallICons,
    outlineVariant = SmallICons,
    scrim = BLACK_DARK.copy(alpha = 0.64f)
)

/**
 * Material Design 3 Shape System
 */
private val AppShapeScheme = Shapes(
    extraSmall = AppShapes.extraSmall,
    small = AppShapes.small,
    medium = AppShapes.medium,
    large = AppShapes.large,
    extraLarge = AppShapes.extraLarge
)

/**
 * Main theme composable for the GitHub Cruise app
 *
 * @param darkTheme Whether to use dark theme (defaults to system preference)
 * @param content The composable content to theme
 */
@Composable
fun GithubCruiseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = AppShapeScheme,
        content = content
    )
}