package com.example.facebookapp.ui.theme.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class PostImageSize(
    var defaultValue: Dp = 0.dp,
    var imageWidth:Dp =  375.dp,
    var imageHeight: Dp = 240.dp,
    var imageCorner: Dp = 15.dp,
)