package com.example.projek_pbb_infinity.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val Colors = lightColorScheme(
    primary = Blue,
    background = Cream
)

@Composable
fun ProjekPbbInfinityTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = Colors,
        content = content
    )
}