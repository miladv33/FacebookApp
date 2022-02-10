package com.example.facebookapp.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.facebookapp.R
import com.example.facebookapp.ui.theme.model.StoryImageSize
import com.example.facebookapp.ui.theme.model.PostImageSize


data class Spacing(
    val defaultValue: Dp = 0.dp,
    val extraSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }
val LocalPostImageSize = compositionLocalOf { PostImageSize() }
val LocalStoryImageSize = compositionLocalOf { StoryImageSize() }
val LocalPostImageId = compositionLocalOf { R.drawable.portrait }
val LocalStoryImageId = compositionLocalOf { R.drawable.portrait }


val MaterialTheme.Spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

val MaterialTheme.PostImageSize: PostImageSize
    @Composable
    @ReadOnlyComposable
    get() = LocalPostImageSize.current

val MaterialTheme.StoryImageSize: StoryImageSize
    @Composable
    @ReadOnlyComposable
    get() = LocalStoryImageSize.current


val MaterialTheme.storyImageId: Int
    @Composable
    @ReadOnlyComposable
    get() = LocalStoryImageId.current

val MaterialTheme.postImageId: Int
    @Composable
    @ReadOnlyComposable
    get() = LocalPostImageId.current
