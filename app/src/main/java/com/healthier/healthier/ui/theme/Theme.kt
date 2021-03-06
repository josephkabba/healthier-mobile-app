package com.healthier.healthier.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(
    val dark: Colors,
    val light: Colors
)

@Immutable
data class CustomTypography(
    val typography: Typography
)

@Immutable
data class CustomShapes(
    val shapes: Shapes
)

/* Other default colors to override
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */

private val DarkColorPalette = darkColors(
    primary = primary,
    secondary = secondary,
    secondaryVariant = darkGreen,
    surface = Color.White,
    background = Color.Black,
    onError = Color.Red,
    error = Color.Red,
)

private val LightColorPalette = lightColors(
    primary = primary,
    secondary = secondary,
    secondaryVariant = lightGreen,
    surface = Color.Black,
    background = Color.White,
    onError = Color.Red,
    error = Color.Red
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        dark = darkColors(),
        light = lightColors()
    )
}

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography( typography = Typography )
}

val LocalCustomShapes = staticCompositionLocalOf {
    CustomShapes(shapes = Shapes)
}

@Composable
fun HealthierTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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

object HealthierTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current

    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current

    val shapes: CustomShapes
        @Composable
        get() = LocalCustomShapes.current
}