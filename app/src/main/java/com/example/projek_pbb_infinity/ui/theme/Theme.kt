package com.example.projek_pbb_infinity.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Blue,
    background = Cream,
    surface = Cream,
    onPrimary = Color.White,
    onBackground = Color.DarkGray,
    onSurface = Color.DarkGray
)

@Composable
fun ProjekPbbInfinityTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}