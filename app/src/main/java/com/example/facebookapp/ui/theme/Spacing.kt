package com.example.facebookapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.facebookapp.R

data class ImageSize(
    val defaultValue: Dp = 0.dp,
    //post
    val postImageWidth: Dp = 375.dp,
    val postImageHeight: Dp = 240.dp,
    val postImageCorner: Dp = 15.dp,
    //story
    val storyImageWidth: Dp = 70.dp,
    val storyImageHeight: Dp = 80.dp,
    val storyImageCorner: Dp = 25.dp,
    )

data class Spacing(
    val defaultValue: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }
val LocalImageSize = compositionLocalOf { ImageSize() }
val LocalPostImageId = compositionLocalOf { R.drawable.portrait }
val LocalStoryImageId = compositionLocalOf { R.drawable.portrait }


val MaterialTheme.Spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

val MaterialTheme.ImageSize: ImageSize
    @Composable
    @ReadOnlyComposable
    get() = LocalImageSize.current

val MaterialTheme.storyImageId: Int
    @Composable
    @ReadOnlyComposable
    get() = LocalStoryImageId.current

val MaterialTheme.postImageId: Int
    @Composable
    @ReadOnlyComposable
    get() = LocalPostImageId.current
