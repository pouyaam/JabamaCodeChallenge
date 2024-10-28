package com.jabama.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Light default theme color scheme
 */

val LightDefaultColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = Yellow,
    error = Red,
    onError = White,
    tertiary = Gray2,
    surface = Gray,
    onSurface = Black,
    background = White,
    onBackground = Gray
)

/**
 * Dark default theme color scheme
 */

val DarkDefaultColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Black,
    secondary = Yellow,
    error = Red,
    onError = White,
    surface = Gray,
    onSurface = Black,
    background = Gray2,
    onBackground = White
)

@Composable
fun JabamaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkDefaultColorScheme
    } else {
        LightDefaultColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )

}