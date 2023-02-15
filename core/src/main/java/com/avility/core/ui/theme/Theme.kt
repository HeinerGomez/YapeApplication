package com.avility.core.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
    primary = ColorPrimaryDark,
    primaryVariant = ColorPrimaryVariantDark,
    background = BackgroundDark,
    onBackground = TextBackgroundDark,
    onPrimary = BackgroundDark,
    secondary = ColorSecondaryDark,
    onSecondary = BackgroundDark,
    secondaryVariant = ColorPrimaryDark,
    surface = ColorSurfaceDark,
    onSurface = ColorOnSurfaceDark
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = ColorPrimaryLight,
    primaryVariant = ColorPrimaryVariantLight,
    background = BackgroundLight,
    onBackground = TextBackgroundLight,
    onPrimary = BackgroundLight,
    secondary = ColorSecondaryLight,
    onSecondary = BackgroundLight,
    secondaryVariant = ColorPrimaryLight,
    surface = ColorSurfaceLight,
    onSurface = ColorOnSurfaceLight
)

@Composable
fun YapeApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}