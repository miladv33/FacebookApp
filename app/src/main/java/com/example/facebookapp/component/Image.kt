package com.example.facebookapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebookapp.R
import com.example.facebookapp.ui.theme.*
import com.example.facebookapp.ui.theme.model.StoryImageSize

@Composable
fun circularImage(imageId: Int = R.drawable.portrait, size: Int = 64, hasStory: Boolean = false) {
    Image(
        painter = painterResource(imageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)                       // clip to the circle shape
            .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
    )
}



@Preview
@Composable
fun storyInMainPage() {
    CompositionLocalProvider {
        StoryImageBanner()
    }
}

@Preview
@Composable
fun storyInUserPage() {
    val imageId = R.drawable.girl
    val imageSize = StoryImageSize(imageHeight = 100.dp)
    CompositionLocalProvider(
        LocalStoryImageSize provides imageSize,
        LocalStoryImageId provides imageId
    ) {
        StoryImageBanner()
    }
}

@Composable
fun StoryImageBanner() {
    Image(
        painter = painterResource(MaterialTheme.storyImageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(MaterialTheme.StoryImageSize.imageWidth)
            .height(MaterialTheme.StoryImageSize.imageHeight)
            .clip(RoundedCornerShape(MaterialTheme.StoryImageSize.imageCorner))
    )
}
@Preview
@Composable
fun PostImageBanner() {
    Image(
        painter = painterResource(MaterialTheme.postImageId),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(MaterialTheme.PostImageSize.imageWidth)
            .height(MaterialTheme.PostImageSize.imageHeight)
            .clip(RoundedCornerShape(MaterialTheme.PostImageSize.imageCorner))
    )
}


