package com.example.facebookapp.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.facebookapp.ui.theme.Purple200
import com.example.facebookapp.ui.theme.Teal200

val localTitle = compositionLocalOf { "this is a title" }
val localTextColor = compositionLocalOf { Purple200 }

/**
 * First title has default variable of localTitle and localTextColor
 *
 */
@Preview
@Composable
fun firstTitle() {
    CompositionLocalProvider {
        ScreenText()
    }
}

/**
 * Second title with CompositionLocalProvider we can change the localText and localColorText
 * and see another results
 *
 */
@Preview
@Composable
fun secondTitle() {
    CompositionLocalProvider(
        localTitle provides ("secondTitle"),
        localTextColor provides Teal200
    ) {
        ScreenText()
    }
}

@Composable
fun ScreenText() {
    Text(text = localTitle.current, color = localTextColor.current)
}